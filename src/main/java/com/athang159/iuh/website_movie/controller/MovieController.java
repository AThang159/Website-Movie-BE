package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
@Tag(name = "Movie Controller", description = "Operations related to movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{movieId}")
    @Operation(summary = "Get movie by ID", description = "Fetch movie details by its unique movie code.")
    public ResponseEntity<ApiResponse<MovieResponse>> getMovieByMovieId(@PathVariable String movieId) {
        MovieResponse movie = movieService.getMovieByMovieCode(movieId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched movie", movie));
    }

    @GetMapping("/now-showing")
    @Operation(summary = "Get now showing movies", description = "Get a list of movies that are currently showing in theaters.")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getNowShowingMovies() {
        List<MovieResponse> movies = movieService.getNowShowingMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched now showing movies", movies));
    }

    @GetMapping("/coming-soon")
    @Operation(summary = "Get coming soon movies", description = "Get a list of movies that are scheduled to be released soon.")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getComingSoonMovies() {
        List<MovieResponse> movies = movieService.getComingSoonMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched coming soon movies", movies));
    }
}
