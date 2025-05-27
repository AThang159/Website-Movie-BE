package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.entity.MovieFormat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieFormatMapper {
    MovieFormatResponse toMovieFormatResponse(MovieFormat movieFormat);
    List<MovieFormatResponse> toMovieFormatResponses(List<MovieFormat> movieFormats);
}
