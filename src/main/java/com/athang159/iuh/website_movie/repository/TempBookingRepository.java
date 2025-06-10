package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.TempBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TempBookingRepository extends JpaRepository<TempBooking, UUID> {
}
