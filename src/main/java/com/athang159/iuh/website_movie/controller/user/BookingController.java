package com.athang159.iuh.website_movie.controller.user;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("bookingControllerUser")
@RequestMapping("/api/user/bookings")
@Tag(name = "User Booking", description = "APIs for user to manage their bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    @Operation(summary = "Get all bookings of the authenticated user",
            description = "Returns a list of bookings made by the currently authenticated user.")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getUserBookings(Authentication authentication) {
        String username = authentication.getName();
        List<BookingResponse> bookings = bookingService.getBookingsByUsername(username);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetch user bookings successfully", bookings));
    }

    @GetMapping("{bookingCode}")
    @Operation(summary = "Get booking details by booking code",
            description = "Returns detailed information of a specific booking based on the booking code.")
    public ResponseEntity<ApiResponse<BookingDetailResponse>> getBooking(@PathVariable String bookingCode) {
        BookingDetailResponse bookingDetailResponse = bookingService.getBookingByCode(bookingCode);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetch booking successfully", bookingDetailResponse));
    }
}
