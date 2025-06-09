package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.BookingRequest;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    public BookingDetailResponse createBooking(BookingRequest bookingRequest);
    public BookingDetailResponse getBookingByCode(String bookingCode);
    public List<BookingResponse> getAllBookings();
    public Long countBookings();
}
