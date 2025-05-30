package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.SeatResponse;
import com.athang159.iuh.website_movie.dto.response.SeatStatusResponse;
import com.athang159.iuh.website_movie.entity.SeatStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = SeatMapper.class)
public interface SeatStatusMapper {
    @Mapping(source = "showtime.id", target = "showtimeId")
    @Mapping(source = "bookingDetail.id", target = "bookingDetailId")
    SeatStatusResponse toSeatResponse(SeatStatus seatStatus);
    List<SeatStatusResponse> toSeatResponses(List<SeatStatus> seatStatuses);
}
