package com.athang159.iuh.website_movie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreationRequest {
    private Long userId;

    private String customerFullName;
    private String customerEmail;
    private String customerPhone;

    private List<Long> seatStatusIds;

    private Double totalPrice;
    private Double serviceFee;
    private Double amount;
    private String paymentMethod;
}
