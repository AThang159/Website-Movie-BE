package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.service.MovieFormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movie-formats")
@RequiredArgsConstructor
public class MovieFormatController {

    private final MovieFormatService movieFormatService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MovieFormatResponse>>> getAllFormats() {
        List<MovieFormatResponse> formats = movieFormatService.getAllMovieFormats();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Fetched all movie formats", formats)
        );
    }
}
