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
public class ShowtimeResponse {
    private UUID id;
    private String movieId;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer duration;
    private Long theaterId;
    private Long roomId;
    private MovieLanguageType language;
    private Long formatId;
    private int price;
}
