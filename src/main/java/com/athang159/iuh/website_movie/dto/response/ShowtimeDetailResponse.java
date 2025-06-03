package com.athang159.iuh.website_movie.dto.response;

import com.athang159.iuh.website_movie.enums.MovieLanguageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private RoomResponse room;
    private MovieLanguageType language;
    private MovieFormatResponse format;
    private int price;
}
