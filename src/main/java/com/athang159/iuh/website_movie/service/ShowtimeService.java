package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ShowtimeService {
//    List<TimeSlotResponse> getAvailableTimeSlots(Long roomId, LocalDate showDate, Long movieId);
    void createShowtime(ShowtimeRequest request);
    List<ShowtimeResponse> findShowtimes(String movieId, LocalDate showDate, Long theaterId, Long roomId);
    ShowtimeResponse findShowtime(UUID id);
    Long countShowtimes();
    List<ShowtimeResponse> getTodayShowtimes();
}
