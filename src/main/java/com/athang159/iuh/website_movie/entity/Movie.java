// model/Movie.java
package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
