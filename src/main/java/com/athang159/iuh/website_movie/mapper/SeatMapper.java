package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.SeatResponse;
import com.athang159.iuh.website_movie.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "seatCode", expression = "java(seat.getRow() + seat.getColumnIndex())")
    SeatResponse toSeatResponse(Seat seat);
    List<SeatResponse> toSeatResponses(List<Seat> seats);
}
