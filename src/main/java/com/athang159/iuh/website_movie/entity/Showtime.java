package com.athang159.iuh.website_movie.entity;

import com.athang159.iuh.website_movie.enums.MovieLanguageType;
import com.athang159.iuh.website_movie.enums.TimeSlot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDate showDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "movie_format_id")
    private MovieFormat format;

    @Enumerated(EnumType.STRING)
    private MovieLanguageType language;

    private int price;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "theater_room_id")
    private TheaterRoom theaterRoom;

    @OneToMany(mappedBy = "showtime")
    private List<SeatStatus> seatStatuses;

    public Showtime(Movie movie,
                    LocalDate showDate,
                    LocalTime startTime,
                    MovieFormat format,
                    MovieLanguageType language,
                    int price,
                    Theater theater,
                    TheaterRoom theaterRoom) {
        this.movie = movie;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration());
        this.format = format;
        this.language = language;
        this.price = price;
        this.theater = theater;
        this.theaterRoom = theaterRoom;
    }
}

