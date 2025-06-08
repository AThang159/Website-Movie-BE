package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeDetailResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class    ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

//    @GetMapping("/available-time-slots")
//    public ResponseEntity<List<TimeSlotResponse>> getAvailableTimeSlots(
//            @RequestParam Long roomId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
//            @RequestParam Long movieId) {
//        return ResponseEntity.ok(showtimeService.getAvailableTimeSlots(roomId, showDate, movieId));
//    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ShowtimeResponse>> getShowtime(@PathVariable UUID id) {
        ShowtimeResponse showtimeResponse = showtimeService.findShowtime(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimeResponse));
    }

    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getTodayShowtimes(){
        List<ShowtimeResponse> showtimeResponses = showtimeService.getTodayShowtimes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimeResponses));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> getShowtimes(
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam(required = false) String movieId,
            @RequestParam(required = false) Long roomId
    ) {
        List<ShowtimeResponse> showtimes = showtimeService.findShowtimes(movieId, showDate, theaterId, roomId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimes));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> createShowtime(@RequestBody ShowtimeRequest request) {
        ShowtimeDetailResponse response = showtimeService.createShowtime(request);
        return ResponseEntity.ok(ApiResponse.success("Tạo suất chiếu thành công", response));
    }

}
