package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.BookingRequest;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.entity.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    public BookingDetailResponse createBooking(BookingRequest bookingRequest);
    public BookingDetailResponse getBookingByCode(String bookingCode);
    public List<BookingResponse> getAllBookings();
    public Long countBookings();
    public List<BookingResponse> getBookingsByUsername(String username);
    public UUID createTempBooking(BookingRequest request);
    BookingRequest getTempBooking(UUID tempBookingId);
}
