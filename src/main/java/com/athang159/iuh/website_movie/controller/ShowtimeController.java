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
    public ResponseEntity<ApiResponse<ShowtimeDetailResponse>> getShowtime(@PathVariable UUID id) {
        ShowtimeDetailResponse showtimeDetailResponse = showtimeService.getShowtimeById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", showtimeDetailResponse));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ShowtimeResponse>>> searchShowtimes(
            @RequestParam(required = false) String movieCode,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) Long roomId
    ) {
        List<ShowtimeResponse> responses = showtimeService.getShowtimesByFilter(movieCode, showDate, theaterId, roomId);
        return ResponseEntity.ok(ApiResponse.success("Danh sách suất chiếu theo bộ lọc", responses));
    }




}
