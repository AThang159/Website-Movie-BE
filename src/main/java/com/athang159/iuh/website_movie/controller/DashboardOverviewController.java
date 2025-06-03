package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.service.BookingService;
import com.athang159.iuh.website_movie.service.MovieService;
import com.athang159.iuh.website_movie.service.TheaterService;
import com.athang159.iuh.website_movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardOverviewController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/overview")
    public ResponseEntity<Map<String, Long>> getTheater() {
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
}
