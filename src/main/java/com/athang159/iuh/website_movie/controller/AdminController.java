package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.*;
import com.athang159.iuh.website_movie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/overview")
    public ResponseEntity<?> getOverview() {
        try {
            Long countMovies = movieService.countMovies();
            Long countUsers = userService.countUsers();
            Long countTheaters = theaterService.countTheaters();
            Long countBookings = bookingService.countBookings();

            Map<String, Long> data = new HashMap<>();
            data.put("countMovies", countMovies);
            data.put("countUsers", countUsers);
            data.put("countTheaters", countTheaters);
            data.put("countBookings", countBookings);

            ApiResponse<Map<String, Long>> response = new ApiResponse<>(true, "Overview fetched successfully", data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<?> response = new ApiResponse<>(false, "Failed to fetch overview: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
