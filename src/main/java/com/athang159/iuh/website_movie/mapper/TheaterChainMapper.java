package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.TheaterChainResponse;
import com.athang159.iuh.website_movie.entity.TheaterChain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TheaterChainMapper {
    TheaterChainResponse toTheaterChainResponse(TheaterChain theaterChain);
    List<TheaterChainResponse> toTheaterChainResponses(List<TheaterChain> theaterChains);
}
