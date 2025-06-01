package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.BookingCreationRequest;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.entity.Booking;
import com.athang159.iuh.website_movie.service.BookingService;
import com.athang159.iuh.website_movie.service.VnPayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private VnPayService vnPayService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody BookingCreationRequest bookingCreationRequest) {
        try {
            System.out.println(bookingCreationRequest);
            long amount = bookingCreationRequest.getAmount().longValue();

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> orderInfoMap = new HashMap<>();
            orderInfoMap.put("userId", bookingCreationRequest.getUserId());
            orderInfoMap.put("customerFullName", bookingCreationRequest.getCustomerFullName());
            orderInfoMap.put("customerEmail", bookingCreationRequest.getCustomerEmail());
            orderInfoMap.put("customerPhone", bookingCreationRequest.getCustomerPhone());
            orderInfoMap.put("seatStatusIds", bookingCreationRequest.getSeatStatusIds());
            orderInfoMap.put("totalPrice", bookingCreationRequest.getTotalPrice());
            orderInfoMap.put("serviceFee", bookingCreationRequest.getServiceFee());
            orderInfoMap.put("paymentMethod", bookingCreationRequest.getPaymentMethod());

            String orderInfo = "";
            try {
                orderInfo = mapper.writeValueAsString(orderInfoMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println(orderInfo);

            String paymentUrl = vnPayService.createPaymentUrl(amount, orderInfo);
            return ResponseEntity.ok(Collections.singletonMap("url", paymentUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
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
        String charset = StandardCharsets.UTF_8.name();
        for (String fieldName : fieldNames) {
            if (hashData.length() > 0) {
                hashData.append('&');
            }
            try {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(params.get(fieldName), charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return new RedirectView("http://localhost:3000/dat-ve/error");
            }
        }

        String myHash = vnPayService.hmacSHA512(hashData.toString());

        String orderInfoJson = params.get("vnp_OrderInfo");

        System.out.println(orderInfoJson);

        String href = "http://localhost:3000";

        BookingCreationRequest bookingCreationRequest = null;
        if (orderInfoJson != null && !orderInfoJson.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();

                Map<String, Object> orderInfoMap = mapper.readValue(orderInfoJson, new TypeReference<Map<String, Object>>() {});

                bookingCreationRequest = mapToBookingCreationRequest(orderInfoMap);

            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("http://localhost:3000/dat-ve/error");
            }
        }

        if (myHash.equalsIgnoreCase(vnp_SecureHash)) {
            if ("00".equals(responseCode)) {
                BookingResponse bookingResponse = null;
                if (bookingCreationRequest != null) {
                    bookingResponse = bookingService.createBooking(bookingCreationRequest);
                }
                return new RedirectView(href + "/thong-tin-ve/" + bookingResponse.getBookingCode());
            } else {
                return new RedirectView(href + "/dat-ve?status=fail");
            }
        } else {
            return new RedirectView(href + "/dat-ve?status=invalid");
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

        Double amount = null;

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
