package com.athang159.iuh.website_movie.entity;

import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.TheaterStatus;
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
    @Enumerated(EnumType.STRING)
    private FormatType format;
    @Enumerated(EnumType.STRING)
    private TheaterStatus status;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "theater")
    private List<Room> rooms = new ArrayList<>();

    public Theater(String name, String address, City city, FormatType format, TheaterStatus status) {
        this.name = name;
        this.address = address;
        this.format = format;
        this.city = city;
        this.status = status;
    }

}
