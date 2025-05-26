package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.SeatResponse;
import com.athang159.iuh.website_movie.entity.SeatStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatStatusMapper {

//    @Mapping(source = "seat.id", target = "id")
//    @Mapping(source = "seat.seatCode", target = "seatCode")
//    @Mapping(source = "seat.type", target = "type")
//    @Mapping(source = "isBooked", target = "isBooked")
//    SeatResponse toSeatResponse(SeatStatus seatStatus);
}
