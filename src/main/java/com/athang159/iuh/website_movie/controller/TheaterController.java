package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.mapper.TheaterMapper;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/theaters")
@CrossOrigin(origins = "*")
public class TheaterController {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TheaterMapper theaterMapper;

    @GetMapping
    public List<TheaterResponse> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        List<TheaterResponse> theaterResponses = theaterMapper.toTheaterResponses(theaters);
        return theaterResponses;
    }

    @GetMapping("/{id}")
    public Optional<Theater> getTheaterById(@PathVariable Long id) {
        return theaterRepository.findById(id);
    }

    @PostMapping
    public Theater createTheater(@RequestBody Theater theater) {
        return theaterRepository.save(theater);
    }


    @PutMapping("/{id}")
    public Theater updateTheater(@PathVariable Long id, @RequestBody Theater theaterDetails) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found with id " + id));
        theater.setName(theaterDetails.getName());
        theater.setAddress(theaterDetails.getAddress());
        theater.setCity(theaterDetails.getCity());
        theater.setLogo(theaterDetails.getLogo());
        return theaterRepository.save(theater);
    }

    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable Long id) {
        theaterRepository.deleteById(id);
    }

    @GetMapping("/{theaterId}/theater-rooms")
    public List<Theater> getTheaterRoomsByTheater(@PathVariable Long theaterId) {
        return roomRepository.findByTheaterId(theaterId);
    }
}
