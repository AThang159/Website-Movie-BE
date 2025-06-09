package com.athang159.iuh.website_movie.repository;


import com.athang159.iuh.website_movie.entity.Showtime;
import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepositoryCustom {
    List<Showtime> findByFilters(String movieCode, LocalDate showDate, Long theaterId, Long roomId);
}

