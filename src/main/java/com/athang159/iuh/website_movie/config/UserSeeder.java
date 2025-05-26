package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder {

    @Autowired
    UserRepository userRepository;

    CommandLineRunner initUserSeeder() {
        return args -> {
            userRepository.save(new User(null, "ADMIN", "ADMIN", "ADMIN"));
            userRepository.save(new User(null, "USER", "USER", "USER"));
        };
    }
}
