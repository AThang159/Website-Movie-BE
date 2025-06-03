package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.entity.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {TicketMapper.class, UserMapper.class})
public interface BookingMapper {
    BookingResponse toBookingResponse(Booking booking);
    List<BookingResponse> toBookingResponses(List<Booking> bookings);
    BookingDetailResponse toBookingDetailResponse(Booking booking);
}
