package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.RoomDetailResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.entity.Room;
import com.athang159.iuh.website_movie.mapper.RoomMapper;
import com.athang159.iuh.website_movie.repository.RoomRepository;
import com.athang159.iuh.website_movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomMapper roomMapper;

    @Override
    public RoomDetailResponse getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow();
        RoomDetailResponse roomDetailResponse = roomMapper.toRoomDetailResponse(room);
        return roomDetailResponse;
    }
}
