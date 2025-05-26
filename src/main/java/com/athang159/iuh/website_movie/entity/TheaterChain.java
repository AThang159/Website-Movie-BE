package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "theaters")
@NoArgsConstructor
@AllArgsConstructor
public class TheaterChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String logo;

    public TheaterChain(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
}
