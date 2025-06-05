package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.service.MovieFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movie-formats")
public class MovieFormatController {

    @Autowired
    MovieFormatService movieFormatService;

    @GetMapping
    public ResponseEntity<List<MovieFormatResponse>> getAllFormats() {
        return ResponseEntity.ok(movieFormatService.getAllMovieFormats());
    }
}
