package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Theater;

import java.util.List;

public interface TheaterService {
    TheaterResponse getTheaterById(Long id);
    List<TheaterResponse> getAllTheaters();
    void deleteTheaterById(Long id);
    List<RoomResponse> getAllRoomsByTheaterId(Long id);
}
