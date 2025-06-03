package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    boolean existsByName(String name);
    Theater findByName(String name);
    @Query("""
    SELECT t FROM Theater t
    WHERE (:cityId IS NULL OR t.city.id = :cityId)""")
    List<Theater> findTheatersByFilter(
            @Param("cityId") Long cityId
    );
}
