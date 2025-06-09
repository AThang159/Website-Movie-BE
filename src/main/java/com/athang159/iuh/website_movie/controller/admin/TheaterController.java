package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.request.TheaterRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.mapper.RoomMapper;
import com.athang159.iuh.website_movie.service.RoomService;
import com.athang159.iuh.website_movie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("theaterControllerAdmin")
@RequestMapping("/api/admin/theaters")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TheaterResponse>>> getAllTheaters() {
        List<TheaterResponse> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(ApiResponse.success("Fetched all movies", theaters));
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<ApiResponse<TheaterDetailResponse>> deleteTheater(@PathVariable Long id) {
        TheaterDetailResponse theaterDetailResponse = theaterService.softDeleteTheater(id);
        return ResponseEntity.ok(ApiResponse.success("Theater deleted successfully", theaterDetailResponse));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<TheaterDetailResponse>> addTheater(@RequestBody TheaterRequest theaterRequest){
        TheaterDetailResponse theaterDetailResponse = theaterService.addTheater(theaterRequest);
        return ResponseEntity.ok(ApiResponse.success("Theater added success", theaterDetailResponse));
    }

    @GetMapping("{id}/rooms")
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getRooms(@PathVariable Long id) {
        List<RoomResponse> roomResponses = roomService.getAllRoomsByTheaterId(id);
        return ResponseEntity.ok(ApiResponse.success("Fetched rooms", roomResponses));
    }

}
