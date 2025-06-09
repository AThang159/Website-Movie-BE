package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse<MovieResponse>> getMovieByMovieId(@PathVariable String movieId) {
        MovieResponse movie = movieService.getMovieByMovieCode(movieId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched movie", movie));
    }

    @GetMapping("/now-showing")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getNowShowingMovies() {
        List<MovieResponse> movies = movieService.getNowShowingMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched now showing movies", movies));
    }

    @GetMapping("/coming-soon")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getComingSoonMovies() {
        List<MovieResponse> movies = movieService.getComingSoonMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched coming soon movies", movies));
    }
}
