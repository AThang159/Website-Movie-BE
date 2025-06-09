package com.athang159.iuh.website_movie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private Long userId;

    private UUID showtimeId;

    private String customerFullName;
    private String customerEmail;
    private String customerPhone;

    private List<Long> seatSelectedIds;

    private Double totalPrice;
    private Double serviceFee;
    private Double amount;
    private String paymentMethod;
}
