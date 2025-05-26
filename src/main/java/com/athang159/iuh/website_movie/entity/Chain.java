package com.athang159.iuh.website_movie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(exclude = "theaters")
@NoArgsConstructor
@AllArgsConstructor
public class Chain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String logo;

    public Chain(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
}
