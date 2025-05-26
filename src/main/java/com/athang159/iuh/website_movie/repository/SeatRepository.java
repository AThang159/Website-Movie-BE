package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Seat;
import com.athang159.iuh.website_movie.entity.Showtime;
import com.athang159.iuh.website_movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Theater> findByRoomId(Long roomId);
}
