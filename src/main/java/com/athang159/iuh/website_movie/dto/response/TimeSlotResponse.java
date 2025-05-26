package com.athang159.iuh.website_movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotResponse {
    private LocalTime startTime;
    private boolean isEmpty;
}
