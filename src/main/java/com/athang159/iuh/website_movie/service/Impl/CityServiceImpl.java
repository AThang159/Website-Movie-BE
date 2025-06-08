package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.CityResponse;
import com.athang159.iuh.website_movie.entity.City;
import com.athang159.iuh.website_movie.mapper.CityMapper;
import com.athang159.iuh.website_movie.repository.CityRepository;
import com.athang159.iuh.website_movie.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityMapper cityMapper;

    @Override
    public List<CityResponse> getAllCities(){
        List<City> cities = cityRepository.findAll();
        List<CityResponse> citiesResponse = cityMapper.toCityResponses(cities);
        return citiesResponse;
    }
}
