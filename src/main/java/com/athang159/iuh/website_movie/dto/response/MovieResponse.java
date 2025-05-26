package com.athang159.iuh.website_movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieResponse {
    private String movieId;
    private String title;
    private String englishTitle;
    private String posterUrl;
    private String backdropUrl;
    private List<String> genres;
    private Integer duration;
    private LocalDate releaseDate;
    private Integer rating;
    private String director;
    private List<String> castList;
    private String trailerUrl;
    private Boolean featured;
    private Boolean isComingSoon;
}
