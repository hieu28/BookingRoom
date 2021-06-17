package com.example.services.impl;

import com.example.models.entities.*;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.MyBookingFindAll;
import com.example.repositories.BookingRepository;
import com.example.repositories.EmployeeRepository;
import com.example.repositories.RoomRepository;
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
    private RoomRepository roomRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
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

    @Override
    public List<MyBookingFindAll> MyGetAllBooking(Long id) {
        List<BookingEntity> bookingEntities = bookingRepository.findAllByIdE(id);
        List<MyBookingFindAll> result = new ArrayList<>();
        for (BookingEntity item : bookingEntities) {
            MyBookingFindAll bookDTO = mapper.map(item, MyBookingFindAll.class);
            result.add(bookDTO);
        }
        List<RoomEntity> roomEntities = roomRepository.findAll();
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        for (MyBookingFindAll myBookingFindAll : result){
            for (RoomEntity item : roomEntities){
                for (EmployeeEntity itemr: employeeEntities) {
                    if (myBookingFindAll.getRoomId()== item.getId()&&myBookingFindAll.getEmployeeId()==id) {
                        myBookingFindAll.setNameRoom(item.getName());
                        myBookingFindAll.setNameAdBooking(itemr.getName());
                    }
                }
            }
        }
        return result;
    }
}
