package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.TheaterChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterChainRepository extends JpaRepository<TheaterChain, Long> {
    TheaterChain findByName(String name);
}
