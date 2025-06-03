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
    private String logo;
    private String status;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "theater")
    private List<Room> rooms = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "chain_id")
    private Chain chain;

    public Theater(String name, String address, City city, String logo, String status, Chain chain) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.logo = logo;
        this.status = status;
        this.chain = chain;
    }

}
