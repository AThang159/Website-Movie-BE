package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.TheaterStatus;
import com.athang159.iuh.website_movie.repository.CityRepository;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TheaterSeeder {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    CityRepository cityRepository;

    CommandLineRunner initTheaterSeeder() {
        return args -> {

            if (!theaterRepository.existsByName("Beta Trần Quang Khải")) {
                Theater theater = new Theater(
                        "Beta Trần Quang Khải",
                        "Tầng 2 & 3, Toà nhà IMC, 62 Đường Trần Quang Khải, Phường Tân Định, Quận 1, TP. Hồ Chí Minh",
                        cityRepository.findByName("Hồ Chí Minh"),
                        FormatType.FORMAT_3D,
                        TheaterStatus.ACTIVE
                        );
                theaterRepository.save(theater);
            }

            if (!theaterRepository.existsByName("Beta Quang Trung")) {
                Theater theater = new Theater(
                        "Beta Quang Trung",
                        "645 Quang Trung, Phường 11, Quận Gò Vấp, Thành phố Hồ Chí Minh",
                        cityRepository.findByName("Hồ Chí Minh"),
                        FormatType.FORMAT_3D,
                        TheaterStatus.ACTIVE
                );
                theaterRepository.save(theater);
            }

            if (!theaterRepository.existsByName("Cinestar Hai Bà Trưng")) {
                Theater theater = new Theater(
                        "Cinestar Hai Bà Trưng",
                        "135 Hai Bà Trưng, P. Bến Nghé, Q.1, Tp. Hồ Chí Minh",
                        cityRepository.findByName("Hồ Chí Minh"),
                        FormatType.FORMAT_3D,
                        TheaterStatus.ACTIVE
                );
                theaterRepository.save(theater);
            }

            if (!theaterRepository.existsByName("Cinestar Quốc Thanh")) {
                Theater theater = new Theater(
                        "Cinestar Quốc Thanh",
                        "271 Nguyễn Trãi, P. Nguyễn Cư Trinh, Q.1, Tp. Hồ Chí Minh",
                        cityRepository.findByName("Hồ Chí Minh"),
                        FormatType.FORMAT_3D,
                        TheaterStatus.INACTIVE
                );
                theaterRepository.save(theater);
            }
        };
    }
}
