package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;
import com.athang159.iuh.website_movie.dto.response.TimeSlotResponse;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeService {
    List<TimeSlotResponse> getAvailableTimeSlots(Long roomId, LocalDate showDate, Long movieId);
    void createShowtime(ShowtimeRequest request);
    List<ShowtimeResponse> findShowtimes(String movieId, LocalDate showDate, Long theaterId, Long theaterRoomId);
}
