package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.RoomDetailResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomDetailResponse getRoomById(Long id);
    List<RoomResponse> getAllRoomsByTheaterId(Long theaterId);
}
