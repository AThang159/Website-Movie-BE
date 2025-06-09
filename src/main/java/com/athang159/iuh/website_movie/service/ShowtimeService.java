package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.ShowtimeDetailResponse;
import com.athang159.iuh.website_movie.dto.response.ShowtimeResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ShowtimeService {
    ShowtimeDetailResponse createShowtime(ShowtimeRequest request);
    List<ShowtimeResponse> getShowtimes();
    ShowtimeDetailResponse getShowtimeById(UUID id);
    List<ShowtimeResponse> getTodayShowtimes();
    ShowtimeDetailResponse updateShowtime(UUID id, ShowtimeRequest request);
    ShowtimeDetailResponse softDeleteShowtime(UUID id);

    List<ShowtimeResponse> getShowtimesByFilter(String movieId, LocalDate showDate, Long theaterId, Long roomId);
}
