package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.entity.MovieFormat;
import com.athang159.iuh.website_movie.repository.MovieFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/movie-formats")
@CrossOrigin(origins = "*")
public class MovieFormatController {

    @Autowired
    private MovieFormatRepository movieFormatRepository;

    @GetMapping
    public List<MovieFormatResponse> getAllFormats() {
        List<MovieFormat> formats = movieFormatRepository.findAll();
        return formats.stream()
                .map(f -> new MovieFormatResponse(f.getId(), f.getName()))
                .collect(Collectors.toList());
    }
}
