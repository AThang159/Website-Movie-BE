package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Room;
import com.athang159.iuh.website_movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);

    List<Theater> findByTheaterId(Long chainId);
}
