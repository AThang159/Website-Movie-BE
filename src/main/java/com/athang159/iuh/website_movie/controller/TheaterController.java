package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/theaters")
@Tag(name = "Theater Controller", description = "Operations related to movie theaters")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Get theater details by ID",
            description = "Fetch detailed information about a specific theater using its unique ID"
    )
    public ResponseEntity<ApiResponse<TheaterDetailResponse>> getTheaterById(@PathVariable Long id) {
        TheaterDetailResponse response = theaterService.getTheaterById(id);
        return ResponseEntity.ok(ApiResponse.success("Theater details", response));
    }

    @GetMapping("/by-city")
    @Operation(
            summary = "Get theaters by city",
            description = "Retrieve a list of all theaters located in a specific city by city ID"
    )
    public ResponseEntity<ApiResponse<List<TheaterResponse>>> getTheatersByCity(@RequestParam Long cityId) {
        List<TheaterResponse> theaters = theaterService.getTheatersByCityId(cityId);
        return ResponseEntity.ok(ApiResponse.success("List of theaters by city", theaters));
    }

}
