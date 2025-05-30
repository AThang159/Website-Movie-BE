package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.SeatStatusResponse;
import com.athang159.iuh.website_movie.entity.SeatStatus;
import com.athang159.iuh.website_movie.mapper.SeatStatusMapper;
import com.athang159.iuh.website_movie.repository.SeatStatusRepository;
import com.athang159.iuh.website_movie.service.SeatStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatStatusServiceImpl implements SeatStatusService {
    @Autowired
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private SeatStatusMapper seatStatusMapper;

    @Override
    public List<SeatStatusResponse> getAllSeatsByShowtime(UUID showtimeId) {
        List<SeatStatus> seatStatuses = seatStatusRepository.findAllByShowtimeId(showtimeId);
        return seatStatusMapper.toSeatResponses(seatStatuses);
    }
}
