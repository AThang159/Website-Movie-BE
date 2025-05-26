package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.dto.response.TimeSlotResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.entity.Showtime;
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

@RestController
@RequestMapping("/api/showtimes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<LocalTime> DEFAULT_TIME_SLOTS = List.of(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0)
    );

    @GetMapping("/available-time-slots")
    public List<TimeSlotResponse> getAvailableTimeSlots(
            @RequestParam Long theaterRoomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Duration movieDuration = Duration.ofMinutes(movie.getDuration());

        List<Showtime> showtimes = showtimeRepository.findByTheaterRoomIdAndShowDate(theaterRoomId, showDate);

        return DEFAULT_TIME_SLOTS.stream()
                .map(candidateStart -> {
                    LocalTime candidateEnd = candidateStart.plus(movieDuration);
                    boolean hasConflict = showtimes.stream().anyMatch(existing -> {
                        LocalTime existingStart = existing.getStartTime();
                        LocalTime existingEnd = existingStart.plusMinutes(existing.getMovie().getDuration());
                        // Kiểm tra trùng giờ
                        return candidateStart.isBefore(existingEnd) && candidateEnd.isAfter(existingStart);
                    });

                    return new TimeSlotResponse(candidateStart, !hasConflict);
                })
                .toList();
    }

    @GetMapping
    public ResponseEntity<List<ShowtimeResponse>> getShowtimes(
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam(required = false) String movieId,
            @RequestParam(required = false) Long theaterRoomId
    ) {
        List<ShowtimeResponse> showtimes = showtimeService.findShowtimes(movieId, showDate, theaterId, theaterRoomId);
        return ResponseEntity.ok(showtimes);
    }


    /**
     * Tạo suất chiếu mới
     */
    @PostMapping
    public ResponseEntity<String> createShowtime(@RequestBody ShowtimeRequest request) {
        showtimeService.createShowtime(request);
        return ResponseEntity.ok("Showtime created successfully");
    }
}
