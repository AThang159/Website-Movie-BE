package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.RoomDetailResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDetailResponse> getRoomById(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @GetMapping("/{roomId}/name")
    public ResponseEntity<String> getRoomByName(@PathVariable Long roomId) {
        String name = roomService.getRoomById(roomId).getName();
        return ResponseEntity.ok(name);
    }
}
