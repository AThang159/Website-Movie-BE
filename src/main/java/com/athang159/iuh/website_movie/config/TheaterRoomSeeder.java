package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Seat;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.entity.TheaterRoom;
import com.athang159.iuh.website_movie.enums.SeatType;
import com.athang159.iuh.website_movie.repository.SeatRepository;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.repository.TheaterRoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TheaterRoomSeeder {

    CommandLineRunner initTheaterRoomSeeder(TheaterRepository theaterRepository, TheaterRoomRepository theaterRoomRepository, SeatRepository seatRepository) {
        return args -> {
            List<TheaterRoom> theaterRooms = new ArrayList<>();
            Theater theater = theaterRepository.findByName("Beta Trần Quang Khải");

            TheaterRoom cinema1 = new TheaterRoom("cinema-1", "Cinema 1", theater);
            cinema1.setSeats(createSeats(10, "A", SeatType.REGULAR, seatRepository, cinema1)); // 10 ghế, hàng A
            theaterRooms.add(cinema1);

            TheaterRoom cinema2 = new TheaterRoom("cinema-2", "Cinema 2", theater);
            cinema2.setSeats(createSeats(10, "B", SeatType.REGULAR, seatRepository, cinema2)); // 10 ghế, hàng B
            theaterRooms.add(cinema2);

            TheaterRoom cinema3 = new TheaterRoom("cinema-3", "Cinema 3", theater);
            cinema3.setSeats(createSeats(12, "C", SeatType.REGULAR, seatRepository, cinema3)); // 12 ghế, hàng C
            theaterRooms.add(cinema3);

            TheaterRoom cinema4 = new TheaterRoom("cinema-4", "Cinema 4", theater);
            cinema4.setSeats(createSeats(12, "D", SeatType.REGULAR, seatRepository, cinema4)); // 12 ghế, hàng D
            theaterRooms.add(cinema4);

            TheaterRoom cinemaVip = new TheaterRoom("cinema-vip", "Cinema VIP", theater);
            cinemaVip.setSeats(createSeats(5, "E", SeatType.VIP, seatRepository, cinemaVip)); // 5 ghế VIP
            theaterRooms.add(cinemaVip);

            theaterRoomRepository.saveAll(theaterRooms);
        };
    }

    private List<Seat> createSeats(int numberOfSeats, String row, SeatType seatType, SeatRepository seatRepository, TheaterRoom theaterRoom) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(null, row + i, seatType, theaterRoom)); // Ví dụ: A1, A2, ..., VIP1, VIP2
            System.out.println(seats.get(i-1));
        }
//        seatRepository.saveAll(seats);
        return seats;
    }
}