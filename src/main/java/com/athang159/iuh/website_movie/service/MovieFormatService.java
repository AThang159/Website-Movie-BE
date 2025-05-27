package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.entity.MovieFormat;

import java.util.List;

public interface MovieFormatService {
    List<MovieFormatResponse> getAllMovieFormats();
}
