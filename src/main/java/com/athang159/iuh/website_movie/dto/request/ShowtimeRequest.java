package com.athang159.iuh.website_movie.dto.request;

import com.athang159.iuh.website_movie.enums.MovieLanguageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeRequest {
    private Long movieId;
    private LocalDate showDate;
    private LocalTime startTime;
    private Long theaterId;
    private Long roomId;
    private MovieLanguageType language;
    private Long formatId;
    private int price;
}
