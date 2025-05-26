package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    boolean existsByName(String name);

    Theater findByName(String name);
}
