package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.request.MovieRequest;
import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.enums.MovieStatus;
import com.athang159.iuh.website_movie.exception.ResourceNotFoundException;
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
    public MovieResponse getMovieByMovieCode(String movieCode) {
        Movie movie = movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new ResourceNotFoundException("Not found movie code: " + movieCode));
        MovieResponse movieResponse = movieMapper.toMovieResponse(movie);
        return movieResponse;
    }

    @Override
    public List<MovieResponse> getNowShowingMovies() {
        List<Movie> movies = movieRepository.findByStatus(MovieStatus.NOW_SHOWING);
        List<MovieResponse> movieResponses = movieMapper.toMovieResponses(movies);
        return movieResponses;
    }

    @Override
    public List<MovieResponse> getComingSoonMovies() {
        List<Movie> movies = movieRepository.findByStatus(MovieStatus.COMING_SOON);
        List<MovieResponse> movieResponses = movieMapper.toMovieResponses(movies);
        return movieResponses;
    }

    @Override
    public Long countMovies(){
        return movieRepository.count();
    }

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie(
                null,
                movieRequest.getMovieCode(),
                movieRequest.getTitle(),
                movieRequest.getEnglishTitle(),
                movieRequest.getPosterUrl(),
                movieRequest.getBackdropUrl(),
                movieRequest.getGenres(),
                movieRequest.getDuration(),
                movieRequest.getReleaseDate(),
                0,
                movieRequest.getDirector(),
                movieRequest.getCastList(),
                movieRequest.getTrailerUrl(),
                movieRequest.getFeatured(),
                movieRequest.getStatus()
        );

        MovieResponse movieResponse = movieMapper.toMovieResponse(movieRepository.save(movie));

        return movieResponse;
    }

    @Override
    public MovieResponse updateMovie(String movieCode, MovieRequest movieRequest) {
        Movie existingMovie = movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new ResourceNotFoundException("Not found movie ID: " + movieRequest.getMovieCode()));


        existingMovie.setTitle(movieRequest.getTitle());
        existingMovie.setEnglishTitle(movieRequest.getEnglishTitle());
        existingMovie.setPosterUrl(movieRequest.getPosterUrl());
        existingMovie.setBackdropUrl(movieRequest.getBackdropUrl());
        existingMovie.setGenres(movieRequest.getGenres());
        existingMovie.setDuration(movieRequest.getDuration());
        existingMovie.setReleaseDate(movieRequest.getReleaseDate());
        existingMovie.setDirector(movieRequest.getDirector());
        existingMovie.setCastList(movieRequest.getCastList());
        existingMovie.setTrailerUrl(movieRequest.getTrailerUrl());
        existingMovie.setFeatured(movieRequest.getFeatured());
        existingMovie.setStatus(movieRequest.getStatus());

        Movie updatedMovie = movieRepository.save(existingMovie);
        return movieMapper.toMovieResponse(updatedMovie);
    }

    @Override
    public MovieResponse softDeleteMovie(String movieCode) {
        Movie movie = movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new ResourceNotFoundException("Not found movie ID:" + movieCode));

        movie.setStatus(MovieStatus.STOPPED);
        movieRepository.save(movie);
        return movieMapper.toMovieResponse(movie);
    }

}
