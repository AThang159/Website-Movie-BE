package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByReleaseDateAfter(LocalDate date);
    List<Movie> findByReleaseDateLessThanEqual(LocalDate date);

    Movie findByTitle(String title);
    Movie findByMovieId(String movieId);

    @Query("""
    SELECT tc.id, tc.name, t.id, t.name
    FROM Showtime s
    JOIN s.theater t
    JOIN t.theaterChain tc
    WHERE s.movie.movieId = :movieId AND t.city = :cityName
    GROUP BY tc.id, tc.name, t.id, t.name
    """)
    List<Object[]> findTheaterChainsAndTheatersByMovieAndCity(
            @Param("movieId") String movieId,
            @Param("cityName") String cityName
    );
}
