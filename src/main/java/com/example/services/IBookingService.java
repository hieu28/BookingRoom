package com.example.services;

import com.example.models.requests.BookingRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.MyBookingFindAll;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IBookingService {
    String save(BookingRequest bookingRequest,long[] listide);
    BookingReponse save(BookingReponse bkrq);
    void deleteList(long[] ids);
    void deleteBooking(Long id);
    BookingReponse getBookingById(Long id);
    List<BookingReponse> getBookingByIdRoom(Long id);
    List<BookingReponse> findAllPaging(Pageable pageable);
    int totallItem();
    List<BookingReponse> findAllBooking();
    List<MyBookingFindAll> MyGetAllBooking(Long id);
}
