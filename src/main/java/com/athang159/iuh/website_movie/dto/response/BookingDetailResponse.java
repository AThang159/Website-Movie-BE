package com.athang159.iuh.website_movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailResponse {
    private Long id;
    private String bookingCode;
    private UserResponse user;
    private String customerFullName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime bookingTime;
    private Double totalPrice;
    private Double serviceFee;
    private Double amount;
    private String paymentMethod;
    private List<TicketResponse> tickets;
}
