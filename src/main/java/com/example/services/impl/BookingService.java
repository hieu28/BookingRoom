package com.example.services.impl;

import com.example.models.entities.BookingDetailEntity;
import com.example.models.entities.BookingEntity;
import com.example.models.entities.EmployeeEntity;
import com.example.models.entities.RoomEntity;
import com.example.models.requests.BookingRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.MyBookingFindAll;
import com.example.repositories.BookingDetailRepository;
import com.example.repositories.BookingRepository;
import com.example.repositories.EmployeeRepository;
import com.example.repositories.RoomRepository;
import com.example.services.IBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public BookingReponse save(BookingRequest bookingRequest) {
        BookingEntity bookingEntity = mapper.map(bookingRequest, BookingEntity.class);
        List<BookingEntity> listbooking = bookingRepository.findAllByIdRoom(bookingEntity.getRoomId());
        List<Long> listt = new ArrayList<>();
        if (listbooking.size() == 0) {
            bookingEntity = bookingRepository.save(bookingEntity);
        } else {
            for (BookingEntity itemr : listbooking) {
                if (bookingEntity.getCheckIn().getHour() < (itemr.getCheckIn()).getHour()
                        && bookingEntity.getCheckOut().getHour() < itemr.getCheckIn().getHour()
                        || bookingEntity.getCheckIn().getHour() > (itemr.getCheckOut()).getHour()
                        && bookingEntity.getCheckOut().getHour() > itemr.getCheckOut().getHour()) {
                    listt.add(itemr.getId());
                    if (listbooking.size() == listt.size()) {
                        bookingEntity = bookingRepository.save(bookingEntity);
                    }
                } else {
                    return null;
                }
            }
        }
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
        List<BookingDetailEntity> ebk = bookingDetailRepository.findAllByIdB(id);
        List<MyBookingFindAll> result = new ArrayList<>();
        for (BookingDetailEntity item : ebk) {
            MyBookingFindAll emplBk = mapper.map(item, MyBookingFindAll.class);
            result.add(emplBk);
        }
        List<BookingEntity> bke = bookingRepository.findAll();
        List<RoomEntity> re = roomRepository.findAll();
        List<EmployeeEntity> ee = employeeRepository.findAll();
        for (MyBookingFindAll myBookingFindAll : result) {
            for (BookingEntity item : bke) {
                    for (RoomEntity itemr : re) {
                        if (myBookingFindAll.getBookingId() == item.getId()
                                && item.getRoomId() == itemr.getId()) {
                            myBookingFindAll.setTitle(item.getTitle());
                            myBookingFindAll.setCheckIn(item.getCheckIn());
                            myBookingFindAll.setCheckOut(item.getCheckOut());
                            myBookingFindAll.setNumberOfMember(item.getNumberOfMember());
                            myBookingFindAll.setNameRoom(itemr.getName());
                            myBookingFindAll.setDescription(item.getDescription());
                            myBookingFindAll.setRoomId(itemr.getId());
                        }
                    }
            }
        }
        return result;
    }

    @Override
    public List<BookingReponse> getBookingByRoomDay(Long id) {
        List<BookingReponse> results = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAllByIdRoom(id);

        for (BookingEntity item : bookingEntities) {
            if(item.getCheckIn().getDayOfMonth()==java.time.OffsetDateTime.now().getDayOfMonth()){
            BookingReponse bookingReponse = mapper.map(item, BookingReponse.class);
            results.add(bookingReponse);
            }
        }
        return results;
    }

    @Override
    public List<BookingReponse> getBookingByRoomByDay(Long id,int day) {
        List<BookingReponse> results = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingRepository.findAllByIdRoom(id);

        for (BookingEntity item : bookingEntities) {
            if (item.getCheckIn().getDayOfMonth() == day) {
                BookingReponse bookingReponse = mapper.map(item, BookingReponse.class);
                results.add(bookingReponse);
            }
        }
        return results;
    }
}
