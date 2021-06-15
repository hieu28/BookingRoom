package com.example.services.impl;

import com.example.models.entities.BookingEntity;
import com.example.models.entities.DepartmentEntity;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.DepartmentReponse;
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
        BookingEntity bookingEntity = mapper.map(bookingReponse, BookingEntity.class);
        bookingEntity = bookingRepository.save(bookingEntity);
        return mapper.map(bookingEntity, BookingReponse.class);
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
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity = bookingRepository.findOneById(id);

        return mapper.map(bookingEntity, BookingReponse.class);
    }

    @Override
    public List<BookingReponse> findAllPaging(Pageable pageable) {
        List<BookingReponse> results = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAll(pageable).getContent();
        for (BookingEntity item: bookingEntities){
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
        List<BookingEntity> bookingsses = bookingRepository.findAll();
        for (BookingEntity item: bookingsses){
            BookingReponse bookingReponse = mapper.map(item,BookingReponse.class);
            resultss.add(bookingReponse);
        }
        return resultss;
    }
}
