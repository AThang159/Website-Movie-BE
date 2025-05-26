// controller/MovieController.java
package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.mapper.MovieMapper;
import com.athang159.iuh.website_movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieByMovieId(@PathVariable String movieId) {
        return movieRepo.findByMovieId(movieId);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepo.save(movie);
    }

    @GetMapping("/now-showing")
    public List<MovieResponse> getNowShowingMovies() {
        LocalDate today = LocalDate.now();
        List<Movie> movies = movieRepo.findByReleaseDateLessThanEqual(today);
        return movieMapper.toMovieResponses(movies);
    }

    @GetMapping("/coming-soon")
    public List<MovieResponse> getComingSoonMovies() {
        LocalDate today = LocalDate.now();
        List<Movie> movies = movieRepo.findByReleaseDateAfter(today);
        return movieMapper.toMovieResponses(movies);
    }
}
