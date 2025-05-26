package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SeatStatus {
    @Id
    private Long id;

    @ManyToOne
    private Showtime showtime;

    @ManyToOne
    private Seat seat;

    private boolean isBooked; // true = đã đặt
}
