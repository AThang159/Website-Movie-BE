package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.entity.TheaterRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRoomRepository extends JpaRepository<TheaterRoom, Long> {
    TheaterRoom findByName(String name);

    List<Theater> findByTheaterId(Long chainId);
}
