package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.TheaterChain;
import com.athang159.iuh.website_movie.repository.TheaterChainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TheaterChainSeeder {

    CommandLineRunner initTheaterChainSeeder(TheaterChainRepository theaterChainRepository) {
        return args -> {
            List <TheaterChain> theaterChains = new ArrayList<>();
            TheaterChain theaterChain1 = new TheaterChain("Cinestar", "");
            TheaterChain theaterChain2 = new TheaterChain("Beta Cinemas", "");
            theaterChains.add(theaterChain1);
            theaterChains.add(theaterChain2);
            theaterChainRepository.saveAll(theaterChains);
        };
    }
}
