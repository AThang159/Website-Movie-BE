package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.ChainResponse;
import com.athang159.iuh.website_movie.entity.Chain;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TheaterMapper.class)
public interface ChainMapper {
    ChainResponse toTheaterChainResponse(Chain chain);
    List<ChainResponse> toTheaterChainResponses(List<Chain> chains);
}
