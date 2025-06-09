package com.athang159.iuh.website_movie.entity;

import com.athang159.iuh.website_movie.enums.RoomStatus;
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
    private String roomCode;
    private String name;
    private RoomStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "room")
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Room(String roomCode, String name, Theater theater, RoomStatus status) {
        this.roomCode = roomCode;
        this.name = name;
        this.theater = theater;
        this.status = status;
    }
}
