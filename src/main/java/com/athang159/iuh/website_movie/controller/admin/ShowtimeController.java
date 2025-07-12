package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeDetailResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController("showtimeControllerAdmin")
@RequestMapping("/api/admin/showtimes")
@Tag(name = "Admin - Showtime Management", description = "APIs for managing showtimes (Admin)")
public class ShowtimeController {
    @Autowired
    ShowtimeService showtimeService;

    @GetMapping("/today")
    @Operation(summary = "Get today's showtimes", description = "Fetch all showtimes scheduled for today")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getTodayShowtimes(){
        List<ShowtimeResponse> showtimeResponses = showtimeService.getTodayShowtimes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimeResponses));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all showtimes", description = "Fetch the complete list of showtimes")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getAllShowtimes() {
        List<ShowtimeResponse> showtimes = showtimeService.getShowtimes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimes));
    }

    @PostMapping("/add")
    @Operation(summary = "Create a new showtime", description = "Add a new showtime to the system")
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> createShowtime(@RequestBody ShowtimeRequest request) {
        ShowtimeDetailResponse response = showtimeService.createShowtime(request);
        return ResponseEntity.ok(ApiResponse.success("Showtime added successfully", response));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update showtime", description = "Update an existing showtime by ID")
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> updateShowtime(
            @PathVariable UUID id,
            @RequestBody ShowtimeRequest request) {
        ShowtimeDetailResponse response = showtimeService.updateShowtime(id, request);
        return ResponseEntity.ok(ApiResponse.success("Showtime updated successfully", response));
    }

    @DeleteMapping("/soft-delete/{id}")
    @Operation(summary = "Soft delete showtime", description = "Mark a showtime as deleted by its ID")
    public ResponseEntity<ApiResponse<Boolean>> deleteShowtime(@PathVariable UUID id) {
        showtimeService.softDeleteShowtime(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Showtime deleted successfully", true));
    }
}
