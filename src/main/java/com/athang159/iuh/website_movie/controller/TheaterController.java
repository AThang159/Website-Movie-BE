package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.mapper.TheaterMapper;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.repository.TheaterRoomRepository;
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
    private TheaterRoomRepository theaterRoomRepository;

    @Autowired
    private TheaterMapper theaterMapper;

    // Lấy tất cả rạp
    @GetMapping
    public List<TheaterResponse> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        List<TheaterResponse> theaterResponses = theaterMapper.toTheaterResponses(theaters);
        return theaterResponses;
    }

    // Lấy rạp theo id
    @GetMapping("/{id}")
    public Optional<Theater> getTheaterById(@PathVariable Long id) {
        return theaterRepository.findById(id);
    }

    // Thêm mới rạp
    @PostMapping
    public Theater createTheater(@RequestBody Theater theater) {
        return theaterRepository.save(theater);
    }


    // Cập nhật rạp
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

    // Xóa rạp
    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable Long id) {
        theaterRepository.deleteById(id);
    }

    @GetMapping("/{theaterId}/theater-rooms")
    public List<Theater> getTheaterRoomsByTheater(@PathVariable Long theaterId) {
        return theaterRoomRepository.findByTheaterId(theaterId);
    }
}
