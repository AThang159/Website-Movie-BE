package com.athang159.iuh.website_movie.repository;


import com.athang159.iuh.website_movie.entity.Showtime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ShowtimeRepositoryCustom {
    List<Showtime> findByFilters(String movieId, LocalDate showDate, Long theaterId, Long roomId);
}

