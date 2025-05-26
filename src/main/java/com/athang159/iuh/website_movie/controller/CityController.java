package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.response.CityResponse;
import com.athang159.iuh.website_movie.entity.City;
import com.athang159.iuh.website_movie.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cities")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<CityResponse> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(city -> new CityResponse(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }
}
