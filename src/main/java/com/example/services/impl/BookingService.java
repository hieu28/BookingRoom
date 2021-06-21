package com.example.services.impl;

import com.example.models.entities.*;
import com.example.models.requests.BookingRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.MyBookingFindAll;
import com.example.repositories.*;
import com.example.services.IBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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
    private EmployeeBookingRepository employeeBookingRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public String save(BookingRequest bookingRequest,long[] listide) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity = mapper.map(bookingRequest, BookingEntity.class);
        List<BookingEntity> listbooking = bookingRepository.findAllByIdRoom(bookingEntity.getRoomId());
        List<Long> Listt = new ArrayList<>();
        for (BookingEntity itemr : listbooking) {
            if (bookingEntity.getCheckIn().getTime()<(itemr.getCheckIn()).getTime()
                    &&bookingEntity.getCheckOut().getTime()<itemr.getCheckIn().getTime()
                    ||bookingEntity.getCheckIn().getTime()>(itemr.getCheckOut()).getTime()
                    &&bookingEntity.getCheckOut().getTime()>itemr.getCheckOut().getTime()){
                Listt.add(itemr.getId());
                if(listbooking.size()==Listt.size()) {
                    bookingEntity = bookingRepository.save(bookingEntity);
                    List<EmployeeBookingEntity> liste = new ArrayList<>();
                    for (long item : listide) {
                        EmployeeBookingEntity empl = new EmployeeBookingEntity();
                        empl.setEmployeeId(item);
                        empl.setBookingId(bookingEntity.getId());
                        empl = mapper.map(empl, EmployeeBookingEntity.class);
                        liste.add(empl);
                    }
                    EmployeeBookingEntity emplad = new EmployeeBookingEntity();
                    emplad.setEmployeeId(bookingEntity.getEmployeeId());
                    emplad.setBookingId(bookingEntity.getId());
                    liste.add(emplad);
                    employeeBookingRepository.saveAll(liste);
                }
            }
            else {
                return "nhập ngày giờ trùng lặp";
            }
        }
        return "thêm thành công";
    }

    @Override
    public BookingReponse save(BookingReponse bkrq) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity = mapper.map(bkrq, BookingEntity.class);
        bookingEntity = bookingRepository.save(bookingEntity);
        return mapper.map(bookingEntity, BookingReponse.class);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public void deleteList(long[] ids) {
        for (long item : ids) {
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
    public List<BookingReponse> getBookingByIdRoom(Long id) {
        List<BookingReponse> results = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAllByIdRoom(id);
        for (BookingEntity item : bookingEntities) {
            BookingReponse bookingReponse = mapper.map(item, BookingReponse.class);
            results.add(bookingReponse);
        }
        return results;
    }

    @Override
    public List<BookingReponse> findAllPaging(Pageable pageable) {
        List<BookingReponse> results = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAll(pageable).getContent();
        for (BookingEntity item : bookingEntities) {
            BookingReponse bookingReponse = mapper.map(item, BookingReponse.class);
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
        for (BookingEntity item : bookingsses) {
            BookingReponse bookingReponse = mapper.map(item, BookingReponse.class);
            resultss.add(bookingReponse);
        }
        return resultss;
    }

    @Override
    public List<MyBookingFindAll> MyGetAllBooking(Long id) {
        List<EmployeeBookingEntity> ebk = employeeBookingRepository.findAllByIdB(id);
        List<MyBookingFindAll> result = new ArrayList<>();
        for (EmployeeBookingEntity item : ebk) {
            MyBookingFindAll emplBk = mapper.map(item, MyBookingFindAll.class);
            result.add(emplBk);
        }
        List<BookingEntity> bke = bookingRepository.findAll();
        List<RoomEntity> re = roomRepository.findAll();
        List<EmployeeEntity> ee = employeeRepository.findAll();
        for (MyBookingFindAll myBookingFindAll : result) {
            for (BookingEntity item : bke) {
                for (EmployeeEntity itemra : ee) {
                    for (RoomEntity itemr : re) {
                        if (myBookingFindAll.getBookingId() == item.getId()
                                && item.getRoomId() == itemr.getId()
                                && item.getEmployeeId() == itemra.getId()) {
                            myBookingFindAll.setTitle(item.getTitle());
                            myBookingFindAll.setCheckIn(item.getCheckIn());
                            myBookingFindAll.setCheckOut(item.getCheckOut());
                            myBookingFindAll.setNumberOfMember(item.getNumberOfMember());
                            myBookingFindAll.setNameRoom(itemr.getName());
                            myBookingFindAll.setDescription(item.getDescription());
                            myBookingFindAll.setRoomId(itemr.getId());
                            myBookingFindAll.setNameAdBooking(itemra.getName());
                        }
                    }
                }
            }
        }
        return result;
    }
}