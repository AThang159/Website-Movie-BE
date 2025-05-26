package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.MovieFormat;
import com.athang159.iuh.website_movie.repository.MovieFormatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieFormatSeeder {

    CommandLineRunner initMovieFormatSeeder(MovieFormatRepository movieFormatRepository ){
        return args -> {
            movieFormatRepository.save(new MovieFormat(null, "2D"));
            movieFormatRepository.save(new MovieFormat(null, "3D"));
        };
    }
}
