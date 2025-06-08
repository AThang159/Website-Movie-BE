package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.BookingCreationRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.service.BookingService;
import com.athang159.iuh.website_movie.service.VnPayService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final VnPayService vnPayService;
    private final BookingService bookingService;
    @Value("${frontend.url}")
    private String frontendUrl;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Map<String, String>>> createPayment(@RequestBody BookingCreationRequest bookingCreationRequest) {
        try {
            long amount = bookingCreationRequest.getAmount().longValue();

            Map<String, Object> orderInfoMap = new LinkedHashMap<>();
            orderInfoMap.put("userId", bookingCreationRequest.getUserId());
            orderInfoMap.put("customerFullName", bookingCreationRequest.getCustomerFullName());
            orderInfoMap.put("customerEmail", bookingCreationRequest.getCustomerEmail());
            orderInfoMap.put("customerPhone", bookingCreationRequest.getCustomerPhone());
            orderInfoMap.put("seatStatusIds", bookingCreationRequest.getSeatStatusIds());
            orderInfoMap.put("totalPrice", bookingCreationRequest.getTotalPrice());
            orderInfoMap.put("serviceFee", bookingCreationRequest.getServiceFee());
            orderInfoMap.put("paymentMethod", bookingCreationRequest.getPaymentMethod());

            ObjectMapper mapper = new ObjectMapper();
            String orderInfo = mapper.writeValueAsString(orderInfoMap);

            String paymentUrl = vnPayService.createPaymentUrl(amount, orderInfo);
            Map<String, String> response = Collections.singletonMap("url", paymentUrl);

            return ResponseEntity.ok(new ApiResponse<>(true, "Payment URL created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Payment creation failed: " + e.getMessage(), null));
        }
    }

    @GetMapping("/vnpay-return")
    public RedirectView vnpayReturn(@RequestParam Map<String, String> params) {
        String vnp_SecureHash = params.remove("vnp_SecureHash");
        String responseCode = params.get("vnp_ResponseCode");
        params.remove("vnp_SecureHashType");

        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        try {
            for (String fieldName : fieldNames) {
                if (hashData.length() > 0) hashData.append('&');
                hashData.append(fieldName).append('=').append(URLEncoder.encode(params.get(fieldName), StandardCharsets.UTF_8.name()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new RedirectView("http://localhost:3000/dat-ve/error");
        }

        String myHash = vnPayService.hmacSHA512(hashData.toString());
        String orderInfoJson = params.get("vnp_OrderInfo");

        BookingCreationRequest bookingCreationRequest = null;

        try {
            if (orderInfoJson != null && !orderInfoJson.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> orderInfoMap = mapper.readValue(orderInfoJson, new TypeReference<Map<String, Object>>() {});
                bookingCreationRequest = mapToBookingCreationRequest(orderInfoMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView(frontendUrl + "/dat-ve/error");
        }

        if (myHash.equalsIgnoreCase(vnp_SecureHash)) {
            if ("00".equals(responseCode)) {
                try {
                    BookingDetailResponse bookingDetailResponse = bookingService.createBooking(bookingCreationRequest);
                    return new RedirectView(frontendUrl + "/thong-tin-ve/" + bookingDetailResponse.getBookingCode());
                } catch (Exception e) {
                    e.printStackTrace();
                    return new RedirectView(frontendUrl + "/dat-ve/error");
                }
            } else {
                return new RedirectView(frontendUrl + "/dat-ve?status=fail");
            }
        } else {
            return new RedirectView(frontendUrl + "/dat-ve?status=invalid");
        }
    }

    private BookingCreationRequest mapToBookingCreationRequest(Map<String, Object> orderInfoMap) {
        Long userId = orderInfoMap.get("userId") == null ? null : Long.valueOf(orderInfoMap.get("userId").toString());
        String customerFullName = (String) orderInfoMap.get("customerFullName");
        String customerEmail = (String) orderInfoMap.get("customerEmail");
        String customerPhone = (String) orderInfoMap.get("customerPhone");

        List<Long> seatStatusIds = new ArrayList<>();
        Object seatStatusObj = orderInfoMap.get("seatStatusIds");
        if (seatStatusObj instanceof List<?>) {
            for (Object obj : (List<?>) seatStatusObj) {
                seatStatusIds.add(Long.valueOf(obj.toString()));
            }
        }

        Double totalPrice = orderInfoMap.get("totalPrice") == null ? null : Double.valueOf(orderInfoMap.get("totalPrice").toString());
        Double serviceFee = orderInfoMap.get("serviceFee") == null ? null : Double.valueOf(orderInfoMap.get("serviceFee").toString());
        String paymentMethod = (String) orderInfoMap.get("paymentMethod");

        return new BookingCreationRequest(
                userId,
                customerFullName,
                customerEmail,
                customerPhone,
                seatStatusIds,
                totalPrice,
                serviceFee,
                totalPrice + serviceFee,
                paymentMethod
        );
    }
}
