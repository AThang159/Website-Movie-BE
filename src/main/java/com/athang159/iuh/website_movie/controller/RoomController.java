package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.entity.Room;
import com.athang159.iuh.website_movie.mapper.RoomMapper;
import com.athang159.iuh.website_movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;

    @GetMapping("/{roomId}")
    public RoomResponse getRoom(@PathVariable Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        RoomResponse roomResponse = roomMapper.toRoomResponse(room);
        return roomResponse;
    }
}
