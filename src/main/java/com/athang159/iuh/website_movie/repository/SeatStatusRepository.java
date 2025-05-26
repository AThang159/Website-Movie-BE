package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {
    @Query("SELECT COUNT(s) FROM SeatStatus s WHERE s.showtime.id = :showtimeId AND s.isBooked = true")
    long countBookedSeats(@Param("showtimeId") Long showtimeId);
}
