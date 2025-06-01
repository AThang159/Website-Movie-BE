package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingByBookingCode(String bookingCode);
}
