package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.MovieFormat;
import com.athang159.iuh.website_movie.repository.MovieFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieFormatSeeder {

    @Autowired
    MovieFormatRepository movieFormatRepository;

    CommandLineRunner initMovieFormatSeeder(){
        return args -> {
            movieFormatRepository.save(new MovieFormat(null, "2D"));
            movieFormatRepository.save(new MovieFormat(null, "3D"));
        };
    }
}
