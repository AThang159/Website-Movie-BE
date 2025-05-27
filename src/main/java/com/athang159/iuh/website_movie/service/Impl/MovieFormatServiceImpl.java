package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.MovieFormatResponse;
import com.athang159.iuh.website_movie.entity.MovieFormat;
import com.athang159.iuh.website_movie.mapper.MovieFormatMapper;
import com.athang159.iuh.website_movie.repository.MovieFormatRepository;
import com.athang159.iuh.website_movie.service.MovieFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieFormatServiceImpl implements MovieFormatService {
    @Autowired
    MovieFormatRepository movieFormatRepository;
    @Autowired
    MovieFormatMapper movieFormatMapper;

    @Override
    public List<MovieFormatResponse> getAllMovieFormats(){
        List<MovieFormat> movieFormats = movieFormatRepository.findAll();
        List<MovieFormatResponse> movieFormatResponses = movieFormatMapper.toMovieFormatResponses(movieFormats);
        return movieFormatResponses;
    }
}
