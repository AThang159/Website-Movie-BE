package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.TheaterRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TheaterDetailResponse>> getTheaterById(@PathVariable Long id) {
        TheaterDetailResponse response = theaterService.getTheaterById(id);
        return ResponseEntity.ok(ApiResponse.success("Chi tiết rạp chiếu", response));
    }

    @GetMapping("/by-city")
    public ResponseEntity<ApiResponse<List<TheaterResponse>>> getTheatersByCity(@RequestParam Long cityId) {
        List<TheaterResponse> theaters = theaterService.getTheatersByCityId(cityId);
        return ResponseEntity.ok(ApiResponse.success("Danh sách rạp theo thành phố", theaters));
    }

}
