package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.mapper.MovieMapper;
import com.athang159.iuh.website_movie.repository.MovieRepository;
import com.athang159.iuh.website_movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> movieResponses = movieMapper.toMovieResponses(movies);
        return movieResponses;
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        MovieResponse movieResponse = movieMapper.toMovieResponse(movie);
        return movieResponse;
    }

    @Override
    public MovieResponse getMovieByMovieId(String movieId) {
        Movie movie = movieRepository.findByMovieId(movieId);
        MovieResponse movieResponse = movieMapper.toMovieResponse(movie);
        return movieResponse;
    }

    @Override
    public List<MovieResponse> getNowShowingMovies() {
        LocalDate now = LocalDate.now();
        List<Movie> movies = movieRepository.findByReleaseDateLessThanEqual(now);
        List<MovieResponse> movieResponses = movieMapper.toMovieResponses(movies);
        return movieResponses;
    }

    @Override
    public List<MovieResponse> getComingSoonMovies() {
        LocalDate now = LocalDate.now();
        List<Movie> movies = movieRepository.findByReleaseDateAfter(now);
        List<MovieResponse> movieResponses = movieMapper.toMovieResponses(movies);
        return movieResponses;
    }
}
