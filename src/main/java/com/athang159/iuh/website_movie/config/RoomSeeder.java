package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Seat;
import com.athang159.iuh.website_movie.entity.Theater;
import com.athang159.iuh.website_movie.entity.Room;
import com.athang159.iuh.website_movie.enums.SeatType;
import com.athang159.iuh.website_movie.repository.SeatRepository;
import com.athang159.iuh.website_movie.repository.TheaterRepository;
import com.athang159.iuh.website_movie.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomSeeder {

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    SeatRepository seatRepository;

    CommandLineRunner initRoomSeeder() {
        return args -> {
            List<Room> rooms = new ArrayList<>();
            Theater theater = theaterRepository.findByName("Beta Trần Quang Khải");

            Room cinema1 = new Room("cinema-1", "Cinema 1", theater);
            cinema1.setSeats(createSeats(10, "A", SeatType.REGULAR, seatRepository, cinema1)); // 10 ghế, hàng A
            rooms.add(cinema1);

            Room cinema2 = new Room("cinema-2", "Cinema 2", theater);
            cinema2.setSeats(createSeats(10, "B", SeatType.REGULAR, seatRepository, cinema2)); // 10 ghế, hàng B
            rooms.add(cinema2);

            Room cinema3 = new Room("cinema-3", "Cinema 3", theater);
            cinema3.setSeats(createSeats(12, "C", SeatType.REGULAR, seatRepository, cinema3)); // 12 ghế, hàng C
            rooms.add(cinema3);

            Room cinema4 = new Room("cinema-4", "Cinema 4", theater);
            cinema4.setSeats(createSeats(12, "D", SeatType.REGULAR, seatRepository, cinema4)); // 12 ghế, hàng D
            rooms.add(cinema4);

            Room cinemaVip = new Room("cinema-vip", "Cinema VIP", theater);
            cinemaVip.setSeats(createSeats(5, "E", SeatType.VIP, seatRepository, cinemaVip)); // 5 ghế VIP
            rooms.add(cinemaVip);

            roomRepository.saveAll(rooms);
        };
    }

    private List<Seat> createSeats(int numberOfSeats, String row, SeatType seatType, SeatRepository seatRepository, Room room) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(null, row + i, seatType, room));
            System.out.println(seats.get(i-1));
        }
        return seats;
    }
}