package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("movieControllerAdmin")
@RequestMapping("/api/admin/movies")
@Tag(name = "Admin - Movie Controller", description = "Movie management (admin)")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    @Operation(
            summary = "Get all movies",
            description = "Returns a list of all movies, including basic information such as title, code, and status."
    )    public ResponseEntity<ApiResponse<List<MovieResponse>>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies();
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetched all movies", movies));
    }

    @PostMapping("/add")
    @Operation(summary = "Add new movie", description = "Add a new movie to the system")
    public ResponseEntity<ApiResponse<MovieResponse>> addMovie(@RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.addMovie(movieRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Added movie", movieResponse));
    }

    @PutMapping("/update/{movieCode}")
    @Operation(summary = "Update movie", description = "Update movie by movieCode")
    public ResponseEntity<ApiResponse<MovieResponse>> updateMovie(
            @Parameter(description = "Movie code to update", example = "lat-mat-8")
            @PathVariable String movieCode,
            @RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.updateMovie(movieCode, movieRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Updated movie", movieResponse));
    }

    @DeleteMapping("/soft-delete/{movieCode}")
    @Operation(summary = "Soft delete movie", description = "Soft delete movie by movieCode")
    public ResponseEntity<ApiResponse<MovieResponse>> deleteMovie(
            @Parameter(description = "Movie code to update", example = "lat-mat-8")
            @PathVariable String movieCode) {
        MovieResponse result = movieService.softDeleteMovie(movieCode);
        return ResponseEntity.ok(new ApiResponse<>(true, "Movie deleted successfully", result));
    }
}
