package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {
    List<SeatStatus> findAllByShowtimeId(UUID showtimeId);
    boolean existsByShowtimeIdAndSeatId(UUID showtimeId, Long seatId);
}
