package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.CityResponse;
import com.athang159.iuh.website_movie.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponse toCityResponse(City city);
    List<CityResponse> toCityResponses(List<City> cities);
}
