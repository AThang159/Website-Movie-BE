package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("bookingControllerAdmin")
@RequestMapping("/api/admin/bookings")
@Tag(name = "Admin - Bookings", description = "APIs for managing bookings (Admin)")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/all")
    @Operation(summary = "Get all bookings", description = "Returns all bookings made in the system")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        try {
            List<BookingResponse> bookings = bookingService.getAllBookings();
            ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "Fetch all bookings successfully", bookings);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<BookingResponse>> response = new ApiResponse<>(false, "Failed to fetch bookings: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(response);
        }
    }
}
