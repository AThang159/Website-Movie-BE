package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.Room;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.mapper.RoomMapper;
import com.athang159.iuh.website_movie.mapper.TheaterMapper;
import com.athang159.iuh.website_movie.repository.RoomRepository;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterMapper theaterMapper;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public TheaterResponse getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id).orElseThrow();
        TheaterResponse theaterResponse = theaterMapper.toTheaterResponse(theater);
        return theaterResponse;
    }

    @Override
    public List<TheaterResponse> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        List<TheaterResponse> theaterResponses = theaterMapper.toTheaterResponses(theaters);
        return theaterResponses;
    }

    @Override
    public void deleteTheaterById(Long id) {
        theaterRepository.deleteById(id);
    }

    @Override
    public List<RoomResponse> getAllRoomsByTheaterId(Long id) {
        List<Room> rooms = roomRepository.findByTheaterId(id);
        List<RoomResponse> roomResponses = roomMapper.toRoomResponse(rooms);
        return roomResponses;
    }
}
