package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.RoomDetailResponse;
import com.athang159.iuh.website_movie.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Room Controller", description = "Manage room details and information")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{roomId}")
    @Operation(summary = "Get room by ID", description = "Retrieve detailed information of a room using its ID")
    public ResponseEntity<ApiResponse<RoomDetailResponse>> getRoomById(@PathVariable Long roomId) {
        RoomDetailResponse room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", room));
    }
}
