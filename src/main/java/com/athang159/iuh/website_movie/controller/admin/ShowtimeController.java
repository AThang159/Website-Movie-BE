package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeDetailResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("showtimeControllerAdmin")
@RequestMapping("/api/admin/showtimes")
public class ShowtimeController {
    @Autowired
    ShowtimeService showtimeService;

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getTodayShowtimes(){
        List<ShowtimeResponse> showtimeResponses = showtimeService.getTodayShowtimes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimeResponses));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getAllShowtimes() {
        List<ShowtimeResponse> showtimes = showtimeService.getShowtimes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimes));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> createShowtime(@RequestBody ShowtimeRequest request) {
        ShowtimeDetailResponse response = showtimeService.createShowtime(request);
        return ResponseEntity.ok(ApiResponse.success("Showtime added successfully", response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> updateShowtime(
            @PathVariable UUID id,
            @RequestBody ShowtimeRequest request) {
        ShowtimeDetailResponse response = showtimeService.updateShowtime(id, request);
        return ResponseEntity.ok(ApiResponse.success("Showtime updated successfully", response));
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteShowtime(@PathVariable UUID id) {
        showtimeService.softDeleteShowtime(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Showtime deleted successfully", true));
    }
}
