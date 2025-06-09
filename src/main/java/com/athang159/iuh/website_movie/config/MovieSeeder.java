package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Movie;
import com.athang159.iuh.website_movie.enums.MovieStatus;
import com.athang159.iuh.website_movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovieSeeder {

    @Autowired
    MovieRepository movieRepository;

    CommandLineRunner initMovieSeeder() {
        return args -> {
            movieRepository.save(new Movie(null,
                    "lat-mat-8",
                    "Lật Mặt 8: Vòng Tay Nắng",
                    "Face Off 8: Embrace Of Light",
                    "https://ext.same-assets.com/2834133890/3452606385.png",
                    "https://ext.same-assets.com/2834133890/backdrop-latmat8.jpg",
                    List.of(""),
                    115,
                    LocalDate.of(2024, 4, 30),
                    88,
                    "",
                    List.of(""),
                    "",
                    true,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "tham-tu-kien",
                    "Thám Tử Kiền: Kỳ Án Không Đầu",
                    "Detective Kien: The Headless Case",
                    "https://ext.same-assets.com/2834133890/408646272.png",
                    "https://ext.same-assets.com/2834133890/backdrop-ttk.jpg",
                    List.of("Hành động", "Hài"),
                    105,
                    LocalDate.of(2024, 4, 28),
                    97,
                    "Nguyễn Văn A",
                    List.of("Diễn viên A", "Diễn viên B"),
                    "https://youtube.com/trailer/ttk",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "thunderbolts",
                    "Thunderbolts: Biệt Đội Sấm Sét",
                    "Thunderbolts",
                    "https://ext.same-assets.com/2834133890/505354355.jpeg",
                    "https://ext.same-assets.com/2834133890/backdrop-thunder.jpg",
                    List.of("Siêu anh hùng", "Hành động"),
                    130,
                    LocalDate.of(2024, 5, 1),
                    83,
                    "Trần Văn B",
                    List.of("Ngôi sao A", "Ngôi sao B"),
                    "https://youtube.com/trailer/thunderbolts",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "shin-cau-be",
                    "Shin Cậu Bé Bút Chì: Bí Ẩn! Học Viện Hoa Lê Tenkasu",
                    "",
                    "https://ext.same-assets.com/2834133890/1404353736.png",
                    "",
                    List.of("Hoạt hình", "Gia đình"),
                    95,
                    LocalDate.of(2024, 5, 2),
                    null,
                    "Đạo diễn Nhật",
                    List.of("Shin", "Bố mẹ Shin"),
                    "",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "dia-dao",
                    "Địa Đạo: Mặt Trời Trong Bóng Tối",
                    "",
                    "https://ext.same-assets.com/2834133890/2490930004.jpeg",
                    "",
                    List.of("Tâm lý", "Chiến tranh"),
                    120,
                    LocalDate.of(2024, 4, 4),
                    87,
                    "Lê Văn C",
                    List.of("Diễn viên C", "Diễn viên D"),
                    "https://youtube.com/trailer/diadao",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "khung-long-xanh",
                    "Khủng Long Xanh Du Hành Thế Giới Truyện Tranh",
                    "",
                    "https://ext.same-assets.com/2834133890/2459205923.jpeg",
                    "",
                    List.of("Hoạt hình", "Phiêu lưu"),
                    85,
                    LocalDate.of(2026, 4, 30),
                    null,
                    "Đạo diễn D",
                    List.of("Dino", "Bé Gấu"),
                    "",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "mat-danh-ke-toan-2",
                    "Mật Danh Kế Toán 2",
                    "The Accountant 2",
                    "https://ext.same-assets.com/2834133890/1234546813.png",
                    "",
                    List.of("Hành động", "Trinh thám"),
                    110,
                    LocalDate.of(2026, 5, 2),
                    null,
                    "John Doe",
                    List.of("Ben Affleck"),
                    "https://youtube.com/trailer/accountant2",
                    false,
                    MovieStatus.COMING_SOON));

            movieRepository.save(new Movie(null,
                    "looney-tunes",
                    "Looney Tunes: Ngày Trái Đất Nổ Tung",
                    "",
                    "https://ext.same-assets.com/2834133890/1089600495.jpeg",
                    "",
                    List.of("Hoạt hình", "Hài hước"),
                    90,
                    LocalDate.of(2026, 4, 25),
                    null,
                    "",
                    List.of(),
                    "",
                    false,
                    MovieStatus.NOW_SHOWING));

            movieRepository.save(new Movie(null,
                    "chuyen-muong-thu-day-be-cuu-bay",
                    "Chuyện Muông Thú Dạy Bé Cừu Bay",
                    "",
                    "https://ext.same-assets.com/2834133890/60262023.jpeg",
                    "",
                    List.of("Hoạt hình", "Thiếu nhi"),
                    80,
                    LocalDate.of(2026, 4, 25),
                    null,
                    "",
                    List.of(),
                    "",
                    false,
                    MovieStatus.NOW_SHOWING));
        };
    }
}
