package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.MovieFormat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieFormatRepository extends JpaRepository<MovieFormat, Long> {
    MovieFormat findByName(String name);
}
