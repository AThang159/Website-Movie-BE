package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.BookingRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking", description = "APIs for handling bookings")
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingCode}")
    @Operation(summary = "Get booking details by booking code", description = "Returns booking detail information based on a unique booking code.")
    public ResponseEntity<ApiResponse<BookingDetailResponse>> getBooking(@PathVariable String bookingCode) {
        try {
            BookingDetailResponse booking = bookingService.getBookingByCode(bookingCode);
            ApiResponse<BookingDetailResponse> response = new ApiResponse<>(true, "Fetch booking successfully", booking);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<BookingDetailResponse> response = new ApiResponse<>(false, "Failed to fetch booking: " + e.getMessage(), null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new booking", description = "Creates a new booking and returns the booking detail.")
    public ResponseEntity<ApiResponse<BookingDetailResponse>> createBooking(@RequestBody @Valid BookingRequest request) {
        try {
            BookingDetailResponse booking = bookingService.createBooking(request);
            ApiResponse<BookingDetailResponse> response = new ApiResponse<>(true, "Booking created successfully", booking);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<BookingDetailResponse> response = new ApiResponse<>(false, "Failed to create booking: " + e.getMessage(), null);
            return ResponseEntity.status(400).body(response);
        }
    }
}


