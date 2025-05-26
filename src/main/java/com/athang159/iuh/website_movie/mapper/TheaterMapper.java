package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Theater;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TheaterMapper {
    @Mapping(source = "city.name", target = "cityName")
    TheaterResponse toTheaterResponse(Theater theater);
    List<TheaterResponse> toTheaterResponses(List<Theater> theaters);
}
