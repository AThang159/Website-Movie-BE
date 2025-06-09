package com.athang159.iuh.website_movie.dto.request;

import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeRequest {
    private String movieCode;
    private LocalDate showDate;
    private LocalTime startTime;
    private Long theaterId;
    private Long roomId;
    private LanguageType language;
    private FormatType format;
    private int price;
}
