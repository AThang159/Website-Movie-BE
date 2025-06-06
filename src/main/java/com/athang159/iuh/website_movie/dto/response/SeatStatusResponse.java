package com.athang159.iuh.website_movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatStatusResponse {
    private Long id;
    private UUID showtimeId;
    private String ticketId;
    private SeatResponse seat;
}
