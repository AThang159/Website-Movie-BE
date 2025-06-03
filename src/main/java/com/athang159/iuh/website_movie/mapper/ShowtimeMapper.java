package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.ShowtimeDetailResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses ={
            MovieMapper.class,
            TheaterMapper.class,
            MovieFormatMapper.class,
            SeatStatusMapper.class,
            RoomMapper.class}
)
public interface ShowtimeMapper {
    @Mapping(expression = "java(showtime.getMovie().getDuration())", target = "duration")
    @Mapping(expression = "java(getSeatsTotal(showtime))", target = "seatsTotal")
    @Mapping(expression = "java(getSeatsAvailable(showtime))", target = "seatsAvailable")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);
    List<ShowtimeResponse> toShowtimeResponses(List<Showtime> showtimes);
    ShowtimeDetailResponse toShowtimeDetailResponse(Showtime showtime);

    default int getSeatsTotal(Showtime showtime) {
        if (showtime.getRoom() == null || showtime.getRoom().getSeats() == null) return 0;
        return showtime.getRoom().getSeats().size();
    }

    default int getSeatsAvailable(Showtime showtime) {
        if (showtime.getSeatStatuses() == null) return 0;
        return (int) showtime.getSeatStatuses().stream()
                .filter(seatStatus -> seatStatus.getTicket() == null)
                .count();
    }

}
