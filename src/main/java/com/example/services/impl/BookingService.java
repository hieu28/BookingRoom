package com.example.services.impl;

import com.example.models.entities.Booking;
import com.example.models.responses.BookingReponse;
import com.example.repositories.BookingRepository;
import com.example.services.IBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BookingReponse save(BookingReponse bookingReponse) {
        Booking booking = new Booking();
        if (bookingReponse.getId() != null) {
            Booking oldBooking = bookingRepository.findOneById(bookingReponse.getId());
            booking = mapper.map(oldBooking,Booking.class);
        } else {
            booking = mapper.map(bookingReponse,Booking.class);
        }
        booking = bookingRepository.save(booking);
        return mapper.map(booking, BookingReponse.class);

    }
    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    @Override
    public void deleteList(long[] ids) {
        for(long item: ids) {
            bookingRepository.deleteById(item);
        }
    }

    @Override
    public BookingReponse getBookingById(Long id) {
        Booking booking = new Booking();
        booking = bookingRepository.findOneById(id);

        return mapper.map(booking, BookingReponse.class);
    }

    @Override
    public List<BookingReponse> findAllPaging(Pageable pageable) {
        List<BookingReponse> results = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll(pageable).getContent();
        for (Booking item: bookings){
            BookingReponse bookingReponse = mapper.map(item,BookingReponse.class);
            results.add(bookingReponse);
        }
        return results;
    }
    @Override
    public int totallItem() {
        return (int) bookingRepository.count();
    }

    @Override
    public List<BookingReponse> findAllBooking() {
        List<BookingReponse> resultss = new ArrayList<>();
        List<Booking> bookingss = bookingRepository.findAll();
        for (Booking item: bookingss){
            BookingReponse bookingReponse = mapper.map(item,BookingReponse.class);
            resultss.add(bookingReponse);
        }

        return resultss;
    }

    @Override
    public BookingReponse getBookingByIdEmployee(Long id) {
        Booking booking = new Booking();
        booking = bookingRepository.findAllByIdEmployee(id);

        return mapper.map(booking, BookingReponse.class);
    }
}
