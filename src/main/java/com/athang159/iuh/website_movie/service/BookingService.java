package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.BookingCreationRequest;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    public BookingResponse createBooking(BookingCreationRequest bookingCreationRequest);
    public BookingResponse getBookingByCode(String bookingCode);
    public List<BookingResponse> getAllBookings();
}
