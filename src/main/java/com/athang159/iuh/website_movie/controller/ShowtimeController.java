package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.dto.response.TimeSlotResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.entity.Showtime;
import com.athang159.iuh.website_movie.mapper.ShowtimeMapper;
import com.athang159.iuh.website_movie.repository.MovieRepository;
import com.athang159.iuh.website_movie.repository.ShowtimeRepository;
import com.athang159.iuh.website_movie.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/showtimes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/available-time-slots")
    public ResponseEntity<List<TimeSlotResponse>> getAvailableTimeSlots(
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam Long movieId) {
        return ResponseEntity.ok(showtimeService.getAvailableTimeSlots(roomId, showDate, movieId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ShowtimeResponse> getShowtime(@PathVariable UUID id) {
        return ResponseEntity.ok(showtimeService.findShowtime(id));
    }

    @GetMapping
    public ResponseEntity<List<ShowtimeResponse>> getShowtimes(
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam(required = false) String movieId,
            @RequestParam(required = false) Long roomId
    ) {
        List<ShowtimeResponse> showtimes = showtimeService.findShowtimes(movieId, showDate, theaterId, roomId);
        return ResponseEntity.ok(showtimes);
    }

    @PostMapping
    public ResponseEntity<String> createShowtime(@RequestBody ShowtimeRequest request) {
        showtimeService.createShowtime(request);
        return ResponseEntity.ok("Showtime created successfully");
    }
}
