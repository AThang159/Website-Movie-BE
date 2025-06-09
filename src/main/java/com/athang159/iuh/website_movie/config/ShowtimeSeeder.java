package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Showtime;
import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.LanguageType;
import com.athang159.iuh.website_movie.enums.ShowtimeStatus;
import com.athang159.iuh.website_movie.exception.ResourceNotFoundException;
import com.athang159.iuh.website_movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ShowtimeSeeder {

    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    RoomRepository roomRepository;

    CommandLineRunner initShowtimeSeeder(){
        return args -> {
            showtimeRepository.save(new Showtime(
                movieRepository.findByMovieCode("lat-mat-8")
                        .orElseThrow(() -> new ResourceNotFoundException("Not found movie ID")),
                LocalDate.of(2025, 6, 6),
                LocalTime.of(10, 0),
                LanguageType.LONG_TIENG_VIET,
                50000,
                theaterRepository.findById(1L).orElseThrow(),
                roomRepository.findById(1L).orElseThrow(),
                            ShowtimeStatus.SCHEDULED)
                    );

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("lat-mat-8")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 6),
                    LocalTime.of(12, 0),
                    LanguageType.LONG_TIENG_VIET,
                    50000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));
            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("tham-tu-kien")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 6),
                    LocalTime.of(14, 0),
                    LanguageType.LONG_TIENG_VIET,
                    75000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(2L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("shin-cau-be")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 6),
                    LocalTime.of(16, 0),
                    LanguageType.LONG_TIENG_VIET,
                    65000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(4L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("lat-mat-8")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 6),
                    LocalTime.of(18, 0),
                    LanguageType.LONG_TIENG_VIET,
                    80000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("tham-tu-kien")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 6),
                    LocalTime.of(20, 0),
                    LanguageType.LONG_TIENG_VIET,
                    90000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(3L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));

            showtimeRepository.save(new Showtime(
                    movieRepository.findByMovieCode("shin-cau-be")
                            .orElseThrow(() -> new RuntimeException("Not found movie ID")),
                    LocalDate.of(2025, 6, 9),
                    LocalTime.of(12, 0),
                    LanguageType.LONG_TIENG_VIET,
                    50000,
                    theaterRepository.findById(1L).orElseThrow(),
                    roomRepository.findById(1L).orElseThrow(),
                    ShowtimeStatus.SCHEDULED));

        };
    }
}
