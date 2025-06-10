package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "temp_bookings")
@Getter
@Setter
public class TempBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Lob
    private String data;

    private LocalDateTime createdAt;
}
