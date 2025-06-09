package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.request.TheaterRequest;
import com.athang159.iuh.website_movie.dto.response.TheaterDetailResponse;
import com.athang159.iuh.website_movie.dto.response.TheaterResponse;
import com.athang159.iuh.website_movie.entity.City;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.enums.TheaterStatus;
import com.athang159.iuh.website_movie.exception.ResourceNotFoundException;
import com.athang159.iuh.website_movie.mapper.TheaterMapper;
import com.athang159.iuh.website_movie.repository.CityRepository;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterMapper theaterMapper;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public TheaterDetailResponse addTheater(TheaterRequest theaterRequest) {
        Theater theater = new Theater();

        City city = cityRepository.findByName(theaterRequest.getCity());
        if (city == null) {
            city = new City();
            city.setName(theaterRequest.getCity());
            city = cityRepository.save(city);
        }

        theater.setName(theaterRequest.getName());
        theater.setAddress(theaterRequest.getAddress());
        theater.setCity(city);
        theater.setStatus(theaterRequest.getStatus());
        Theater savedTheater = theaterRepository.save(theater);

        return theaterMapper.toTheaterDetailResponse(savedTheater);
    }

    @Override
    public TheaterDetailResponse softDeleteTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found theater Id:" + id));
        theater.setStatus(TheaterStatus.INACTIVE);
        theaterRepository.save(theater);
        TheaterDetailResponse theaterDetailResponse = theaterMapper.toTheaterDetailResponse(theater);
        return theaterDetailResponse;
    }

    @Override
    public TheaterDetailResponse updateTheater(Long id, TheaterRequest theaterRequest){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found theater Id:" + id));

        City city = cityRepository.findByName(theaterRequest.getCity());
        if (city == null) {
            city = new City();
            city.setName(theaterRequest.getCity());
            city = cityRepository.save(city);
        }

        theater.setName(theaterRequest.getName());
        theater.setAddress(theaterRequest.getAddress());
        theater.setFormat(theaterRequest.getFormat());
        theater.setCity(city);
        theater.setStatus(theaterRequest.getStatus());
        theaterRepository.save(theater);

        TheaterDetailResponse theaterDetailResponse = theaterMapper.toTheaterDetailResponse(theater);
        return theaterDetailResponse;
    }

    @Override
    public TheaterDetailResponse getTheaterById(Long id) {
        Theater theater = theaterRepository.findById(id).orElseThrow();
        TheaterDetailResponse theaterDetailResponse = theaterMapper.toTheaterDetailResponse(theater);
        return theaterDetailResponse;
    }

    @Override
    public List<TheaterResponse> getAllTheaters() {
        List<Theater> theaters = theaterRepository.findAll();
        List<TheaterResponse> theaterResponses = theaterMapper.toTheaterResponses(theaters);
        return theaterResponses;
    }

    @Override
    public Long countTheaters() {
        return theaterRepository.count();
    }

    @Override
    public List<TheaterResponse> getTheatersByCityId(Long cityId) {
        return theaterMapper.toTheaterResponses(theaterRepository.findTheatersByFilter(cityId));
    }



}
