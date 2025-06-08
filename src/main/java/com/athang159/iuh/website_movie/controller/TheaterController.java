package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/theaters")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TheaterResponse>>> getAllTheaters(
            @RequestParam(required = false) Long cityId) {
        List<TheaterResponse> theaters = theaterService.getTheatersByCityId(cityId);
        return ResponseEntity.ok(ApiResponse.success("Danh sách rạp chiếu", theaters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TheaterDetailResponse>> getTheaterById(@PathVariable Long id) {
        TheaterDetailResponse response = theaterService.getTheaterById(id);
        return ResponseEntity.ok(ApiResponse.success("Chi tiết rạp chiếu", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheaterById(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa rạp thành công", null));
    }

    @GetMapping("/{theaterId}/rooms")
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getAllRoomsByTheaterId(@PathVariable Long theaterId) {
        List<RoomResponse> rooms = theaterService.getAllRoomsByTheaterId(theaterId);
        return ResponseEntity.ok(ApiResponse.success("Danh sách phòng chiếu của rạp", rooms));
    }

    @GetMapping("/{theaterId}/name")
    public ResponseEntity<ApiResponse<String>> getTheaterName(@PathVariable Long theaterId) {
        String name = theaterService.getTheaterName(theaterId);
        return ResponseEntity.ok(ApiResponse.success("Tên rạp", name));
    }
}
