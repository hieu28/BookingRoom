package com.example.services;

import com.example.models.requests.BookingRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.EmployeeResponse;
import com.example.models.responses.MyBookingFindAll;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.List;

public interface IBookingService {

    BookingReponse save(BookingRequest bookingRequest);

    void deleteList(long[] ids);

    void deleteBooking(Long id);

    BookingReponse getBookingById(Long id);

    List<BookingReponse> getBookingByIdRoom(Long id);

    List<BookingReponse> findAllPaging(Pageable pageable);

    int totallItem();

    List<BookingReponse> findAllBooking();

    List<MyBookingFindAll> MyGetAllBooking(Long id);

    List<BookingReponse> getBookingByRoomDay(Long id);

    List<BookingReponse> getBookingByRoomByDay(Long id , int day);
}
