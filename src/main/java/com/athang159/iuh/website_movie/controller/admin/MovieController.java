package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("movieControllerAdmin")
@RequestMapping("/api/admin/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MovieResponse>>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched all movies", movies));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<MovieResponse>> addMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.addMovie(movieRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Added movie", movieResponse));
    }

    @PutMapping("/update/{movieCode}")
    public ResponseEntity<ApiResponse<MovieResponse>> updateMovie(@PathVariable String movieCode,
                                                                  @RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.updateMovie(movieCode, movieRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Updated movie", movieResponse));
    }

    @DeleteMapping("/soft-delete/{movieCode}")
    public ResponseEntity<ApiResponse<MovieResponse>> deleteMovie(@PathVariable String movieCode) {
        MovieResponse result = movieService.softDeleteMovie(movieCode);
        return ResponseEntity.ok(new ApiResponse<>(true, "Movie deleted successfully", result));
    }
}
