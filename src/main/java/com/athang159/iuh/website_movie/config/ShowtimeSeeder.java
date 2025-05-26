package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Showtime;
import com.athang159.iuh.website_movie.enums.MovieLanguageType;
import com.athang159.iuh.website_movie.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ShowtimeSeeder {

    CommandLineRunner initShowtimeSeeder(
            ShowtimeRepository showtimeRepository,
            MovieRepository movieRepository,
            TheaterRepository theaterRepository,
            MovieFormatRepository movieFormatRepository,
            RoomRepository roomRepository){
        return args -> {
            showtimeRepository.save(new Showtime(
                movieRepository.findByMovieId("lat-mat-8"),
                LocalDate.of(2025, 5, 26),
                LocalTime.of(10, 0),
                movieFormatRepository.findByName("2D"),
                MovieLanguageType.LONG_TIENG_VIET,
                50000,
                theaterRepository.findById(1L).orElseThrow(),
                roomRepository.findById(1L).orElseThrow()));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("lat-mat-8"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(12, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    50000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow()));
            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("tham-tu-kien"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(14, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    75000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(2L).orElseThrow()));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("shin-cau-be"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(16, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    65000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(4L).orElseThrow()));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("lat-mat-8"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(18, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    80000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow()));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("tham-tu-kien"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(20, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    90000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(3L).orElseThrow()));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieId("shin-cau-be"),
                    LocalDate.of(2025, 5, 26),
                    LocalTime.of(12, 0),
                    movieFormatRepository.findByName("2D"),
                    MovieLanguageType.LONG_TIENG_VIET,
                    50000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow()));

        };
    }
}
