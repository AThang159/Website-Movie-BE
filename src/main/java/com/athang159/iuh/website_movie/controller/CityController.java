package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.CityResponse;
import com.athang159.iuh.website_movie.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@Tag(name = "City Controller", description = "Operations related to cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @Operation(summary = "Get all cities", description = "Retrieve a list of all available cities")
    public ResponseEntity<ApiResponse<List<CityResponse>>> getAllCities() {
        List<CityResponse> cities = cityService.getAllCities();
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", cities));
    }
}
