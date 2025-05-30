package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(0)  // chạy đầu tiên
public class DatabaseSeeder {

    @Autowired
    private CitySeeder citySeeder;
    @Autowired
    private MovieFormatSeeder movieFormatSeeder;
    @Autowired
    private ChainSeeder chainSeeder;
    @Autowired
    private RoomSeeder roomSeeder;
    @Autowired
    private MovieSeeder movieSeeder;
    @Autowired
    private TheaterSeeder theaterSeeder;
    @Autowired
    private ShowtimeSeeder showtimeSeeder;
    @Autowired
    private UserSeeder userSeeder;
    @Autowired
    private SeatStatusSeeder seatStatusSeeder;

    @Bean
    CommandLineRunner runAllSeeders() {
        return args -> {
            citySeeder.initCitySeeder().run(args);
            movieFormatSeeder.initMovieFormatSeeder().run(args);
            chainSeeder.initChainSeeder().run(args);
            theaterSeeder.initTheaterSeeder().run(args);
            roomSeeder.initRoomSeeder().run(args);
            movieSeeder.initMovieSeeder().run(args);
            showtimeSeeder.initShowtimeSeeder().run(args);
            seatStatusSeeder.initSeatStatusSeeder().run(args);
            userSeeder.initUserSeeder().run(args);
        };
    }
}
