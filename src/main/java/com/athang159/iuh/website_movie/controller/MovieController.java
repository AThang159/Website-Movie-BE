// controller/MovieController.java
package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieByMovieId(@PathVariable String movieId) {

        return ResponseEntity.ok(movieService.getMovieByMovieId(movieId));
    }

    @GetMapping("/now-showing")
    public ResponseEntity<List<MovieResponse>> getNowShowingMovies() {
        return ResponseEntity.ok(movieService.getNowShowingMovies());
    }

    @GetMapping("/coming-soon")
    public ResponseEntity<List<MovieResponse>> getComingSoonMovies() {
        return ResponseEntity.ok(movieService.getComingSoonMovies());
    }
}
