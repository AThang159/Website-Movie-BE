package com.athang159.iuh.website_movie.config;

import com.athang159.iuh.website_movie.entity.Seat;
import com.athang159.iuh.website_movie.entity.SeatStatus;
import com.athang159.iuh.website_movie.entity.Showtime;
import com.athang159.iuh.website_movie.repository.SeatRepository;
import com.athang159.iuh.website_movie.repository.SeatStatusRepository;
import com.athang159.iuh.website_movie.repository.ShowtimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatStatusSeeder {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private SeatStatusRepository seatStatusRepository;

    @Transactional
    CommandLineRunner initSeatStatusSeeder(){
        return args ->{
            List<Showtime> showtimes = showtimeRepository.findAll();
            for (Showtime showtime : showtimes) {
                List<Seat> seats = seatRepository.findAllByRoomId(showtime.getRoom().getId());

                for (Seat seat : seats) {
                    boolean exists = seatStatusRepository.existsByShowtimeIdAndSeatId(showtime.getId(), seat.getId());
                    if (!exists) {
                        SeatStatus seatStatus = new SeatStatus();
                        seatStatus.setShowtime(showtime);
                        seatStatus.setSeat(seat);
                        seatStatusRepository.save(seatStatus);
                    }
                }
            }
        };
    }

}
