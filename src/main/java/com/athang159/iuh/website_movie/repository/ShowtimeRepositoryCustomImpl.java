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

public class ShowtimeRepositoryCustomImpl implements ShowtimeRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Showtime> findByFilters(String movieId, LocalDate showDate, Long theaterId, Long roomId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Showtime> cq = cb.createQuery(Showtime.class);
        Root<Showtime> root = cq.from(Showtime.class);

        List<Predicate> predicates = new ArrayList<>();

        if (roomId != null) {
            predicates.add(cb.equal(root.get("room").get("id"), roomId));
        }

        if (showDate != null) {
            predicates.add(cb.equal(root.get("showDate"), showDate));
        }

        if (theaterId != null) {
            predicates.add(cb.equal(root.get("theater").get("id"), theaterId));
        }

        if (StringUtils.hasText(movieId)) {
            predicates.add(cb.equal(root.get("movie").get("movieId"), movieId));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
