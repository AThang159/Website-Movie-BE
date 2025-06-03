package com.athang159.iuh.website_movie.dto.response;

import java.util.List;

public class TheaterDetailResponse {
    private Long id;
    private String name;
    private String address;
    private CityResponse city;
    private String status;
    private List<RoomResponse> rooms;
}
