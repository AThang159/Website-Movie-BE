package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.request.BookingCreationRequest;
import com.athang159.iuh.website_movie.dto.response.BookingResponse;
import com.athang159.iuh.website_movie.entity.Booking;
import com.athang159.iuh.website_movie.entity.Ticket;
import com.athang159.iuh.website_movie.entity.SeatStatus;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.mapper.BookingMapper;
import com.athang159.iuh.website_movie.repository.BookingRepository;
import com.athang159.iuh.website_movie.repository.SeatStatusRepository;
import com.athang159.iuh.website_movie.repository.UserRepository;
import com.athang159.iuh.website_movie.service.BookingService;
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
    private SeatStatusRepository seatStatusRepository;
    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingResponse createBooking(BookingCreationRequest bookingCreationRequest){
        Booking booking = new Booking();
        String bookingCode = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        booking.setBookingCode(bookingCode);
        if (bookingCreationRequest.getUserId() != null) {
            User user = userRepository.findById(bookingCreationRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            booking.setUser(user);
        }
        booking.setCustomerFullName(bookingCreationRequest.getCustomerFullName());
        booking.setCustomerEmail(bookingCreationRequest.getCustomerEmail());
        booking.setCustomerPhone(bookingCreationRequest.getCustomerPhone());
        booking.setBookingTime(LocalDateTime.now());
        booking.setServiceFee(bookingCreationRequest.getServiceFee());
        booking.setPaymentMethod(bookingCreationRequest.getPaymentMethod());

        List<Ticket> details = new ArrayList<>();
        double total = 0;

        for (Long seatStatusId : bookingCreationRequest.getSeatStatusIds()) {
            SeatStatus seatStatus = seatStatusRepository.findById(seatStatusId)
                    .orElseThrow(() -> new RuntimeException("SeatStatus not found"));

            if (seatStatus.getTicket() != null) {
                throw new RuntimeException("Seat already booked");
            }

            Ticket detail = new Ticket();
            detail.setBooking(booking);
            detail.setSeatStatus(seatStatus);

            seatStatus.setTicket(detail);

            details.add(detail);
            total += seatStatus.getShowtime().getPrice();
        }
        booking.setTickets(details);
        booking.setTotalPrice(total);
        bookingRepository.save(booking);
        BookingResponse bookingResponse = bookingMapper.toBookingResponse(booking);
        return bookingResponse;
    }

    @Override
    public BookingResponse getBookingByCode(String bookingCode){
        Booking booking = bookingRepository.findBookingByBookingCode(bookingCode);
        return bookingMapper.toBookingResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingResponses(bookings);
    }
}
