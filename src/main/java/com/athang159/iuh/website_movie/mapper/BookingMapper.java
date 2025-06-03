package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TicketMapper.class, UserMapper.class, ShowtimeMapper.class})
public abstract class BookingMapper {

    @Autowired
    protected ShowtimeMapper showtimeMapper;

    @Mapping(target = "showtime", expression = "java(getShowtime(booking))")
    public abstract BookingResponse toBookingResponse(Booking booking);

    public abstract List<BookingResponse> toBookingResponses(List<Booking> bookings);

    public abstract BookingDetailResponse toBookingDetailResponse(Booking booking);

    protected ShowtimeResponse getShowtime(Booking booking) {
        if (booking == null || booking.getTickets() == null || booking.getTickets().isEmpty()) {
            return null;
        }
        return showtimeMapper.toShowtimeResponse(booking.getTickets().get(0).getSeatStatus().getShowtime());
    }
}
