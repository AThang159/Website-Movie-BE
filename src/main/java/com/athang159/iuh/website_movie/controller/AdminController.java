package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.*;
import com.athang159.iuh.website_movie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<Map<String, Long>> getOverview() {
        Long countMovies = movieService.countMovies();
        Long countUsers = movieService.countMovies();
        Long countTheaters = theaterService.countTheaters();
        Long countBookings = bookingService.countBookings();
        Map<String, Long> map = new HashMap<>();
        map.put("countMovies", countMovies);
        map.put("countUsers", countUsers);
        map.put("countTheaters", countTheaters);
        map.put("countBookings", countBookings);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/showtimes")
    public ResponseEntity<List<ShowtimeResponse>> getShowtimes(
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate,
            @RequestParam(required = false) String movieId,
            @RequestParam(required = false) Long roomId
    ) {
        List<ShowtimeResponse> showtimes = showtimeService.findShowtimes(movieId, showDate, theaterId, roomId);
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/theaters")
    public ResponseEntity<List<TheaterResponse>> getAllTheater(@RequestParam(required=false) Long cityId) {
        return ResponseEntity.ok(theaterService.getTheatersByCityId(cityId));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
