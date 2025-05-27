package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAllMovies();
    MovieResponse getMovieById(Long id);
    MovieResponse getMovieByMovieId(String movieId);
    List<MovieResponse> getNowShowingMovies();
    List<MovieResponse> getComingSoonMovies();
}
