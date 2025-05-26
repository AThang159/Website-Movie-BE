package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "rooms")
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "theater")
    private List<Room> rooms = new ArrayList<>();

    private String logo;

    @ManyToOne
    @JoinColumn(name = "theater_chain_id")
    private TheaterChain theaterChain;

    public Theater(String name, String address, City city, String logo, TheaterChain theaterChain) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.logo = logo;
        this.theaterChain = theaterChain;
    }
}
