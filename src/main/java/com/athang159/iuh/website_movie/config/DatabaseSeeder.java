package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(0)  // chạy đầu tiên
public class DatabaseSeeder {

    private final CitySeeder citySeeder;
    private final MovieFormatSeeder movieFormatSeeder;
    private final TheaterChainSeeder theaterChainSeeder;
    private final RoomSeeder roomSeeder;
    private final MovieSeeder movieSeeder;
    private final TheaterSeeder theaterSeeder;
    private final ShowtimeSeeder showtimeSeeder;
    private final UserSeeder userSeeder;

    public DatabaseSeeder(
            CitySeeder citySeeder,
            MovieFormatSeeder movieFormatSeeder,
            TheaterChainSeeder theaterChainSeeder,
            RoomSeeder roomSeeder,
            MovieSeeder movieSeeder,
            TheaterSeeder theaterSeeder,
            ShowtimeSeeder showtimeSeeder,
            UserSeeder userSeeder) {
        this.citySeeder = citySeeder;
        this.movieFormatSeeder = movieFormatSeeder;
        this.theaterChainSeeder = theaterChainSeeder;
        this.roomSeeder = roomSeeder;
        this.movieSeeder = movieSeeder;
        this.theaterSeeder = theaterSeeder;
        this.showtimeSeeder = showtimeSeeder;
        this.userSeeder = userSeeder;
    }

    @Bean
    CommandLineRunner runAllSeeders(
            CityRepository cityRepository,
            MovieFormatRepository movieFormatRepository,
            RoomRepository roomRepository,
            MovieRepository movieRepository,
            TheaterChainRepository theaterChainRepository,
            TheaterRepository theaterRepository,
            ShowtimeRepository showtimeRepository,
            SeatRepository seatRepository,
            UserRepository userRepository) {
        return args -> {
            citySeeder.initCitySeeder(cityRepository).run(args);
            movieFormatSeeder.initMovieFormatSeeder(movieFormatRepository).run(args);
            theaterChainSeeder.initTheaterChainSeeder(theaterChainRepository).run(args);
            theaterSeeder.initTheaterSeeder(theaterRepository, cityRepository, theaterChainRepository).run(args);
            roomSeeder.initRoomSeeder(theaterRepository, roomRepository, seatRepository).run(args);
            movieSeeder.initMovieSeeder(movieRepository).run(args);
            showtimeSeeder.initShowtimeSeeder(showtimeRepository, movieRepository, theaterRepository, movieFormatRepository, roomRepository).run(args);
            userSeeder.initUserSeeder(userRepository).run(args);
        };
    }
}
