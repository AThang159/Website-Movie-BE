package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAllMovies();
    MovieResponse getMovieById(Long id);
    MovieResponse getMovieByMovieCode(String movieCode);
    List<MovieResponse> getNowShowingMovies();
    List<MovieResponse> getComingSoonMovies();
    Long countMovies();
    MovieResponse addMovie(MovieRequest movieRequest);
    MovieResponse updateMovie(String movieCode, MovieRequest movieRequest);
    MovieResponse softDeleteMovie(String movieCode);
}
