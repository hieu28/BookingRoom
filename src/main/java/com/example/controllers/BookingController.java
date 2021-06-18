package com.example.controllers;

import com.example.models.responses.BookingReponse;
import com.example.models.responses.BookingfindAllPagReponse;
import com.example.models.responses.RoomFindAllIndex;
import com.example.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookingController {
    @Autowired
    private IBookingService iBookingService;
    @PostMapping(value = "/booking")
    public BookingReponse createBooking(@RequestBody BookingReponse model) {
        return iBookingService.save(model);
    }
    @PutMapping(value = "/booking/{id}")

    public BookingReponse updateBooking(@RequestBody BookingReponse model, @PathVariable("id") long id) {
        model.setId(id);
        return iBookingService.save(model);
    }

    @DeleteMapping(value = "/booking")
    public void deleteNew(@RequestBody long[] ids) {
        iBookingService.deleteList(ids);
    }

    @DeleteMapping(value = "/booking/{id}")
    public String deleteBooking(@PathVariable("id") long id) {
        iBookingService.deleteBooking(id);
//        return new RoomRepository<Room>(HttpStatus.NO_CONTENT);
        return "xóa thành công";
    }

    @GetMapping(value = "/booking/{id}")
    public BookingReponse getById(@PathVariable("id") long id) {

        return iBookingService.getBookingById(id);
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

}
