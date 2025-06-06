package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingCode;
    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;
    private String customerEmail;
    private String customerPhone;
    private String customerFullName;
    private LocalDateTime bookingTime;
    private Double totalPrice;
    private Double serviceFee;
    private Double amount;
    private String paymentMethod;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}

