package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Theater;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class, RoomMapper.class})
public interface TheaterMapper {
    @Mapping(target = "totalRooms", expression = "java(theater.getRooms() != null ? theater.getRooms().size() : 0)")
    TheaterResponse toTheaterResponse(Theater theater);
    List<TheaterResponse> toTheaterResponses(List<Theater> theaters);
    TheaterDetailResponse toTheaterDetailResponse(Theater theater);
}
