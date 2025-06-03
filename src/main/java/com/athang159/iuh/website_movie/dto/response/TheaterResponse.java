package com.athang159.iuh.website_movie.dto.response;

import com.athang159.iuh.website_movie.entity.Chain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponse {
    private Long id;
    private String name;
    private String address;
    private CityResponse city;
}
