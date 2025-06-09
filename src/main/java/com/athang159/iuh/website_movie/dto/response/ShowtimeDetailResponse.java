package com.athang159.iuh.website_movie.dto.response;

import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeDetailResponse {
    private UUID id;
    private MovieResponse movie;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer duration;
    private TheaterResponse theater;
    private RoomDetailResponse room;
    private LanguageType language;
    private int price;
    private List<TicketResponse> tickets;
    private String status;
}
