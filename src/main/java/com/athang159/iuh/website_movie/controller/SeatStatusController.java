package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.SeatStatusResponse;
import com.athang159.iuh.website_movie.service.SeatStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/seat-statuses")
public class SeatStatusController {

    @Autowired
    SeatStatusService seatStatusService;

    @GetMapping("/showtime/{showtimeId}")
    public ResponseEntity<ApiResponse<List<SeatStatusResponse>>> getSeatsByShowtime(@PathVariable UUID showtimeId) {
        List<SeatStatusResponse> responses = seatStatusService.getAllSeatsByShowtime(showtimeId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", responses));
    }
}
