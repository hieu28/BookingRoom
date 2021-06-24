package com.example.services.impl;

import com.example.models.entities.BookingDetailEntity;
import com.example.models.requests.BookingDetailRequest;
import com.example.models.responses.BookingReponse;
import com.example.repositories.BookingDetailRepository;
import com.example.services.IBookingDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailService implements IBookingDetailService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;


    @Override
    public BookingReponse save(BookingDetailRequest bookingDetail) {

        BookingDetailEntity bookingDetailEntity = new BookingDetailEntity();
        bookingDetailEntity = mapper.map(bookingDetail, BookingDetailEntity.class);
        bookingDetailEntity = bookingDetailRepository.save(bookingDetailEntity);
        return mapper.map(bookingDetailEntity, BookingReponse.class);

    }
}
