package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {
    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "theater.id", target = "theaterId")
    @Mapping(expression = "java(showtime.getMovie().getDuration())", target = "duration")
    @Mapping(source = "theaterRoom.id", target = "theaterRoomId")
    @Mapping(source = "format.id", target = "formatId")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);
    List<ShowtimeResponse> toShowtimeResponses(List<Showtime> showtimes);
}
