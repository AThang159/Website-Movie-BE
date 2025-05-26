package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Chain;
import com.athang159.iuh.website_movie.repository.ChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChainSeeder {

    @Autowired
    private ChainRepository chainRepository;

    CommandLineRunner initChainSeeder() {
        return args -> {
            List <Chain> chains = new ArrayList<>();
            Chain chain1 = new Chain("Cinestar", "");
            Chain chain2 = new Chain("Beta Cinemas", "");
            chains.add(chain1);
            chains.add(chain2);
            chainRepository.saveAll(chains);
        };
    }
}
