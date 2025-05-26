package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/theater-rooms")
public class TheaterRoomController {

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/{theaterRoomId}/seats")
    public List<Theater> getSeatsByTheaterRoom(@PathVariable Long theaterRoomId) {
        return seatRepository.findByTheaterRoomId(theaterRoomId);
    }
}
