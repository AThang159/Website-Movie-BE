package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.BookingRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.service.BookingService;
import com.athang159.iuh.website_movie.service.VnPayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@Tag(name = "Payment Controller", description = "Handle VNPay payment operations")
@RequiredArgsConstructor
public class PaymentController {

    private final VnPayService vnPayService;
    private final BookingService bookingService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @PostMapping("/create")
    @Operation(summary = "Create VNPay payment URL", description = "Generate a payment URL for the given booking request")
    public ResponseEntity<ApiResponse<Map<String, String>>> createPayment(@RequestBody BookingRequest bookingRequest) {
        try {
            UUID tempBookingId = bookingService.createTempBooking(bookingRequest);

            String orderInfo = "tempId=" + tempBookingId;

            String paymentUrl = vnPayService.createPaymentUrl(bookingRequest.getAmount().longValue(), orderInfo);
            Map<String, String> response = Collections.singletonMap("url", paymentUrl);

            return ResponseEntity.ok(new ApiResponse<>(true, "Payment URL created", response));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>(false, "Payment creation failed: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/vnpay-return")
    @Operation(summary = "VNPay return endpoint", description = "Handle VNPay return response and redirect to frontend")
    public RedirectView vnpayReturn(@RequestParam Map<String, String> params) {
        String vnp_SecureHash = params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        String responseCode = params.get("vnp_ResponseCode");

        try {
            String myHash = vnPayService.hmacSHA512(buildHashData(params));
            if (!myHash.equalsIgnoreCase(vnp_SecureHash)) {
                return new RedirectView(frontendUrl + "/dat-ve?status=invalid");
            }

            if ("00".equals(responseCode)) {
                String orderInfo = URLDecoder.decode(params.get("vnp_OrderInfo"), StandardCharsets.UTF_8);
                UUID tempBookingId = UUID.fromString(orderInfo.split("=")[1]);

                BookingRequest request = bookingService.getTempBooking(tempBookingId);
                BookingDetailResponse booking = bookingService.createBooking(request);

                return new RedirectView(frontendUrl + "/thong-tin-ve/" + booking.getBookingCode());
            }

            return new RedirectView(frontendUrl + "/dat-ve?status=fail");

        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView(frontendUrl + "/dat-ve/error");
        }
    }


    private String buildHashData(Map<String, String> params) throws Exception {
        List<String> sortedKeys = new ArrayList<>(params.keySet());
        Collections.sort(sortedKeys);

        StringBuilder sb = new StringBuilder();
        for (String key : sortedKeys) {
            if (sb.length() > 0) sb.append('&');
            sb.append(key).append('=').append(URLEncoder.encode(params.get(key), StandardCharsets.UTF_8));
        }
        return sb.toString();
    }
}
