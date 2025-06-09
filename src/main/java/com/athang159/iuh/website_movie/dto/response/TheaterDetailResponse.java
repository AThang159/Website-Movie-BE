package com.athang159.iuh.website_movie.dto.response;

import com.athang159.iuh.website_movie.enums.FormatType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TheaterDetailResponse {
    private Long id;
    private String name;
    private String address;
    private FormatType format;
    private CityResponse city;
    private List<RoomResponse> rooms;
    private String status;
}
