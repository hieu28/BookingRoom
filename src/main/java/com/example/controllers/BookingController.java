package com.example.controllers;

import com.example.models.requests.BookingRequest;
import com.example.models.requests.RoomRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.BookingfindAllPagReponse;
import com.example.models.responses.MyBookingFindAll;
import com.example.models.responses.RoomResponse;
import com.example.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class BookingController {
    @Autowired
    private IBookingService iBookingService;
    @PostMapping(value = "/booking")
    public String createBooking(@RequestBody BookingRequest model,@RequestParam long[] ids) {
        return iBookingService.save(model,ids);
    }
    @PutMapping(value = "/booking/{id}")
    public BookingReponse updateNew(@RequestBody BookingReponse model, @PathVariable("id") long id) {
        model.setId(id);
        return iBookingService.save(model);
    }
    @DeleteMapping(value = "/booking")
    public void deleteBooking(@RequestBody long[] ids) {
        iBookingService.deleteList(ids);
    }
    @DeleteMapping(value = "/booking/{id}")
    public String deleteBooking(@PathVariable("id") long id) {
        iBookingService.deleteBooking(id);
        return "xóa thành công";
    }
    @GetMapping(value = "/booking/{id}")
    public BookingReponse getById(@PathVariable("id") long id) {

        return iBookingService.getBookingById(id);
    }
    @GetMapping(value = "/bookingByIdRoom/{id}")
    public List<BookingReponse> getByIdRoom(@PathVariable("id") long id) {
        return iBookingService.getBookingByIdRoom(id);
    }
    @GetMapping(value = "/booking")
    public BookingfindAllPagReponse ShowBookingPaging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        BookingfindAllPagReponse result = new BookingfindAllPagReponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page-1,limit);
        result.setListresult(iBookingService.findAllPaging(pageable));
        result.setTotalpage((int)Math.ceil((double)(iBookingService.totallItem())/limit));
        return result;
    }
    @GetMapping(value = "/booking/getall")
    public List<BookingReponse> ShowBookingAll() {
        return iBookingService.findAllBooking();
    }
    @GetMapping(value = "/mybooking/{id}")
    public List<MyBookingFindAll> ShowMyBooking(@PathVariable("id") long id) {
        return iBookingService.MyGetAllBooking(id);
    }
}
