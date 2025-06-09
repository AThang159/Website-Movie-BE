package com.athang159.iuh.website_movie.entity;

import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.LanguageType;
import com.athang159.iuh.website_movie.enums.ShowtimeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private LocalDate showDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "showtime")
    private List<Ticket> tickets;

    private int price;

    @Enumerated(EnumType.STRING)
    private ShowtimeStatus status;

    public Showtime(Movie movie,
                    LocalDate showDate,
                    LocalTime startTime,
                    LanguageType language,
                    int price,
                    Theater theater,
                    Room room,
                    ShowtimeStatus status) {
        this.movie = movie;
        this.showDate = showDate;
        this.startTime = startTime;
        this.language = language;
        this.price = price;
        this.theater = theater;
        this.room = room;
        this.status = status;
    }
}

