package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.SeatStatusResponse;

import java.util.List;
import java.util.UUID;

public interface SeatStatusService {
    List<SeatStatusResponse> getAllSeatsByShowtime(UUID showtimeId);
}
