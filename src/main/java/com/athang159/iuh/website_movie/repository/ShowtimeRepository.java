package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, UUID>, ShowtimeRepositoryCustom {
    List<Showtime> findByRoomIdAndShowDateOrderByStartTime(Long roomId, LocalDate showDate);
    List<Showtime> findByRoomIdAndShowDate(Long roomId, LocalDate showDate);
    List<Showtime> findByShowDateBetween(LocalDate start, LocalDate end);
}
