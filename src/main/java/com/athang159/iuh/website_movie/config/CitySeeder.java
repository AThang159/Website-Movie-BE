package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.City;
import com.athang159.iuh.website_movie.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CitySeeder {

    @Autowired
    CityRepository cityRepository;

    CommandLineRunner initCitySeeder() {
        return args -> {
          cityRepository.save(new City(null, "Hồ Chí Minh"));

          cityRepository.save(new City(null, "Bình Dương"));
        };
    }
}
