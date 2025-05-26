package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "room")
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Room(String roomId, String name, Theater theater) {
        this.roomId = roomId;
        this.name = name;
        this.theater = theater;
    }
}
