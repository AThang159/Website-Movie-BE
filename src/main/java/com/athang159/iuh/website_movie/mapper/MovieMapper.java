package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.MovieResponse;
import com.athang159.iuh.website_movie.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse toMovieResponse(Movie movie);
    List<MovieResponse> toMovieResponses(List<Movie> movies);
}
