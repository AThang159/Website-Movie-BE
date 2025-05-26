package com.athang159.iuh.website_movie.dto.response;

import com.athang159.iuh.website_movie.enums.MovieLanguageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeResponse {
    private Long movieId;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer duration;
    private Long theaterId;
    private Long theaterRoomId;
    private MovieLanguageType language;
    private Long formatId;
    private int price;
}
