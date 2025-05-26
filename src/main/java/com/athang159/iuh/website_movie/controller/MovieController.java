// controller/MovieController.java
package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterChainWithTheatersResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.mapper.MovieMapper;
import com.athang159.iuh.website_movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/{movieId}/cities/{cityName}/theater-chains")
    public List<TheaterChainWithTheatersResponse> getTheaterChainsByMovieAndCity(
            @PathVariable String movieId,
            @PathVariable String cityName
    ) {
        List<TheaterChainWithTheatersResponse> result = new ArrayList<>();

        // Giả sử bạn có custom query sau:
        List<Object[]> rawResults = movieRepo.findTheaterChainsAndTheatersByMovieAndCity(movieId, cityName);

        Map<Long, TheaterChainWithTheatersResponse> map = new HashMap<>();

        for (Object[] row : rawResults) {
            Long chainId = ((Number) row[0]).longValue();
            String chainName = (String) row[1];
            Long theaterId = ((Number) row[2]).longValue();
            String theaterName = (String) row[3];

            map.computeIfAbsent(chainId, k -> {
                TheaterChainWithTheatersResponse chain = new TheaterChainWithTheatersResponse();
                chain.setTheaterChainId(chainId);
                chain.setTheaterChainName(chainName);
                chain.setTheaters(new ArrayList<>());
                return chain;
            });

            TheaterChainWithTheatersResponse.TheaterInfo info = new TheaterChainWithTheatersResponse.TheaterInfo();
            info.setTheaterId(theaterId);
            info.setTheaterName(theaterName);

            map.get(chainId).getTheaters().add(info);
        }

        return new ArrayList<>(map.values());
    }

}
