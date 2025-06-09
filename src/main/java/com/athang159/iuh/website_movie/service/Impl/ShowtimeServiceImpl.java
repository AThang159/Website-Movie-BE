package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.request.ShowtimeRequest;
import com.athang159.iuh.website_movie.dto.response.*;
import com.athang159.iuh.website_movie.entity.*;
import com.athang159.iuh.website_movie.enums.ShowtimeStatus;
import com.athang159.iuh.website_movie.exception.ResourceNotFoundException;
import com.athang159.iuh.website_movie.mapper.ShowtimeMapper;
import com.athang159.iuh.website_movie.repository.*;
import com.athang159.iuh.website_movie.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowtimeMapper showtimeMapper;

    @Autowired
    private SeatRepository seatRepository;

    private final LocalTime OPEN_TIME = LocalTime.of(8, 0);
    private final LocalTime CLOSE_TIME = LocalTime.of(23, 0);
    private final int GAP_MINUTES = 10;

    @Override
    public ShowtimeDetailResponse createShowtime(ShowtimeRequest request) {
        Movie movie = movieRepository.findByMovieCode(request.getMovieCode())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        Theater theater = theaterRepository.findById(request.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));

        LocalTime endTime = request.getStartTime().plusMinutes(movie.getDuration());

        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(request.getStartTime());
        showtime.setEndTime(endTime);
        showtime.setTheater(theater);
        showtime.setShowDate(request.getShowDate());
        showtime.setLanguage(request.getLanguage());
        showtime.setPrice(request.getPrice());

        showtimeRepository.save(showtime);

        ShowtimeDetailResponse showtimeDetailResponse = showtimeMapper.toShowtimeDetailResponse(showtime);

        return showtimeDetailResponse;
    }

    @Override
    public ShowtimeDetailResponse updateShowtime(UUID id, ShowtimeRequest request) {
        Showtime existing = showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found"));

        Movie movie = movieRepository.findByMovieCode(request.getMovieCode())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        Theater theater = theaterRepository.findById(request.getTheaterId())
                .orElseThrow(() -> new ResourceNotFoundException("Theater not found"));

        LocalTime endTime = request.getStartTime().plusMinutes(movie.getDuration());

        existing.setMovie(movie);
        existing.setRoom(room);
        existing.setStartTime(request.getStartTime());
        existing.setEndTime(endTime);
        existing.setTheater(theater);
        existing.setShowDate(request.getShowDate());
        existing.setLanguage(request.getLanguage());
        existing.setPrice(request.getPrice());

        showtimeRepository.save(existing);

        return showtimeMapper.toShowtimeDetailResponse(existing);
    }


    @Override
    public ShowtimeDetailResponse softDeleteShowtime(UUID id) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found with ID: " + id));
        showtime.setStatus(ShowtimeStatus.CANCELLED);
        showtimeRepository.save(showtime);
        ShowtimeDetailResponse showtimeDetailResponse = showtimeMapper.toShowtimeDetailResponse(showtime);
        return showtimeDetailResponse;
    }

//    @Override
//    public List<TimeSlotResponse> getAvailableTimeSlots(Long roomId, LocalDate showDate, Long movieId) {
//        Movie movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new RuntimeException("Movie not found"));
//
//        Room room = roomRepository.findById(roomId)
//                .orElseThrow(() -> new RuntimeException("Room not found"));
//
//        int durationMinutes = movie.getDuration();
//
//        List<Showtime> showtimes = showtimeRepository
//                .findByRoomIdAndShowDateOrderByStartTime(roomId, showDate);
//
//        List<TimeSlotResponse> availableSlots = new ArrayList<>();
//
//        LocalTime currentStart = OPEN_TIME;
//        LocalTime closingTime = CLOSE_TIME;
//
//        for (Showtime showtime : showtimes) {
//            LocalTime bookedStart = showtime.getStartTime();
//            LocalTime bookedEnd = showtime.getEndTime();
//
//            LocalTime potentialEnd = currentStart.plusMinutes(durationMinutes);
//
//            if (!potentialEnd.isAfter(bookedStart)) {
//                availableSlots.add(new TimeSlotResponse(currentStart, false));
//            }
//
//            currentStart = bookedEnd.plusMinutes(GAP_MINUTES);
//        }
//
//        if (!currentStart.plusMinutes(durationMinutes).isAfter(closingTime)) {
//            availableSlots.add(new TimeSlotResponse(currentStart, true));
//        }
//
//        return availableSlots;
//    }

    @Override
    public List<ShowtimeResponse> getShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAll();
        List<ShowtimeResponse> showtimesResponses = showtimeMapper.toShowtimeResponses(showtimes);

        return showtimesResponses;
    }

    @Override
    public ShowtimeDetailResponse getShowtimeById(UUID id){
        Showtime showtime = showtimeRepository.findById(id).orElseThrow();
        ShowtimeDetailResponse showtimeDetailResponse = showtimeMapper.toShowtimeDetailResponse(showtime);
        return showtimeDetailResponse;
    }

    @Override
    public List<ShowtimeResponse> getTodayShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findByShowDateBetween(LocalDate.now(), LocalDate.now());
        List<ShowtimeResponse> showtimeResponses = showtimeMapper.toShowtimeResponses(showtimes);
        return showtimeResponses;
    }

    @Override
    public List<ShowtimeResponse> getShowtimesByFilter(String movieCode, LocalDate showDate, Long theaterId, Long roomId) {
        List<Showtime> showtimes = showtimeRepository.findByFilters(movieCode, showDate, theaterId, roomId);

        return showtimeMapper.toShowtimeResponses(showtimes);
    }
}
