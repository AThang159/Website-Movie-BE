package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Chain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRepository extends JpaRepository<Chain, Long> {
    Chain findByName(String name);
}
