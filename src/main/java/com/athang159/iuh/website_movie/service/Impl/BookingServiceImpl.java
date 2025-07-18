package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.request.BookingRequest;
import com.athang159.iuh.website_movie.dto.response.BookingDetailResponse;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.entity.*;
import com.athang159.iuh.website_movie.exception.ResourceNotFoundException;
import com.athang159.iuh.website_movie.mapper.BookingMapper;
import com.athang159.iuh.website_movie.repository.*;
import com.athang159.iuh.website_movie.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TempBookingRepository tempBookingRepository;

    @Override
    public BookingDetailResponse createBooking(BookingRequest bookingRequest){
        Booking booking = new Booking();
        String bookingCode = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Showtime showtime = showtimeRepository.findById(bookingRequest.getShowtimeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found showtime Id: " +
                                bookingRequest.getShowtimeId()));


        booking.setBookingCode(bookingCode);
        if (bookingRequest.getUserId() != null) {
            User user = userRepository.findById(bookingRequest.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            booking.setUser(user);
        }
        booking.setCustomerFullName(bookingRequest.getCustomerFullName());
        booking.setCustomerEmail(bookingRequest.getCustomerEmail());
        booking.setCustomerPhone(bookingRequest.getCustomerPhone());
        booking.setBookingTime(LocalDateTime.now());

        List<Ticket> details = new ArrayList<>();
        for (Long seatId : bookingRequest.getSeatSelectedIds()){
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found seat Id: " + seatId));
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setShowtime(showtime);
            ticket.setBooking(booking);
            details.add(ticket);
        }

        booking.setTickets(details);
        booking.setTotalPrice(bookingRequest.getTotalPrice());
        booking.setServiceFee(bookingRequest.getServiceFee());
        booking.setPaymentMethod(bookingRequest.getPaymentMethod());
        booking.setAmount(bookingRequest.getAmount());
        bookingRepository.save(booking);
        BookingDetailResponse bookingDetailResponse = bookingMapper.toBookingDetailResponse(booking);
        return bookingDetailResponse;
    }

    @Override
    public BookingDetailResponse getBookingByCode(String bookingCode){
        Booking booking = bookingRepository.findBookingByBookingCode(bookingCode);
        return bookingMapper.toBookingDetailResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingResponses(bookings);
    }

    @Override
    public Long countBookings(){
        Long count = bookingRepository.count();
        return count;
    }

    @Override
    public List<BookingResponse> getBookingsByUsername(String username){
        List<Booking> bookings = bookingRepository.findBookingByUser_Username(username);
        return bookingMapper.toBookingResponses(bookings);
    }

    @Override
    public UUID createTempBooking(BookingRequest request) {
        try {
            TempBooking tempBooking = new TempBooking();
            String json = objectMapper.writeValueAsString(request);
            tempBooking.setData(json);
            tempBooking.setCreatedAt(LocalDateTime.now());

            tempBookingRepository.save(tempBooking);
            return tempBooking.getId();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot serialize BookingRequest", e);
        }
    }

    @Override
    public BookingRequest getTempBooking(UUID tempBookingId) {
        TempBooking tempBooking = tempBookingRepository.findById(tempBookingId)
                .orElseThrow(() -> new RuntimeException("Temp booking not found"));

        try {
            return objectMapper.readValue(tempBooking.getData(), BookingRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing booking data", e);
        }
    }
}
