package com.athang159.iuh.website_movie.controller;

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
@CrossOrigin(origins = "*")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

//    @GetMapping
//    public ResponseEntity<List<TheaterResponse>> getAllTheaters() {
//        return ResponseEntity.ok(theaterService.getAllTheaters());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterDetailResponse> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheaterById(id);
        return ResponseEntity.ok("Theater deleted successfully");
    }

    @GetMapping("/{theaterId}/rooms")
    public ResponseEntity<List<RoomResponse>> getAllRoomsByTheaterId(@PathVariable Long theaterId) {
        return ResponseEntity.ok(theaterService.getAllRoomsByTheaterId(theaterId));
    }

    @GetMapping("{theaterId}/name")
    public ResponseEntity<String> getTheaterName(@PathVariable Long theaterId) {
        return ResponseEntity.ok(theaterService.getTheaterName(theaterId));
    }

    @GetMapping
    public ResponseEntity<List<TheaterResponse>> getTheatersByCityId(@RequestParam Long cityId) {
        return ResponseEntity.ok(theaterService.getTheatersByCityId(cityId));
    }
}
