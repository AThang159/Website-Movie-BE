package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.TheaterRequest;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;

import java.util.List;

public interface TheaterService {
    TheaterDetailResponse getTheaterById(Long id);
    List<TheaterResponse> getAllTheaters();
    TheaterDetailResponse softDeleteTheater(Long id);
    Long countTheaters();
    List<TheaterResponse> getTheatersByCityId(Long cityId);
    TheaterDetailResponse addTheater(TheaterRequest theaterRequest);
    TheaterDetailResponse updateTheater(Long id, TheaterRequest theaterRequest);
}
