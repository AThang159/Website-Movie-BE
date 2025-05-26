package com.athang159.iuh.website_movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRoomResponse {
    private Long id;
    private String name;
    private LocalTime availableStartTime;
}
