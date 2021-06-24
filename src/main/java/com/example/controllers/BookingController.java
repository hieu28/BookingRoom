package com.example.controllers;

import com.example.models.requests.BookingDetailRequest;
import com.example.models.requests.BookingRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.BookingfindAllPagReponse;
import com.example.models.responses.EmployeeResponse;
import com.example.models.responses.MyBookingFindAll;

import com.example.services.IBookingDetailService;

import com.example.services.IBookingService;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import java.util.List;

@CrossOrigin
@RestController
public class BookingController {

    @Autowired
    private IBookingService iBookingService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IBookingDetailService bookingDetailService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/booking/employee")
    public List<Long> addEmployee(@RequestBody Long[] ide, HttpServletRequest request) {
        List<Long> listEmpl = (List<Long>) request.getSession().getAttribute("employee");
        for (Long id : ide){
            if (listEmpl==null){
                listEmpl = new ArrayList<>();
                listEmpl.add(id);
            } else {
                listEmpl.add(id);
            }
        }
        request.getSession().setAttribute("employee",listEmpl);
        return listEmpl;

    }

    @DeleteMapping("/booking/employee/{id}")
    public String delete(@PathVariable("id") Long id,HttpServletRequest request){
        List<Long> listEmpl = (List<Long>) request.getSession().getAttribute("employee");
        listEmpl.remove(id);
        return "Xoa thanh cong";
    }


    @GetMapping(value = "/view-employee")
    public List<EmployeeResponse> viewEmployee(HttpServletRequest request){
        List<Long> listEmpl = (List<Long>) request.getSession().getAttribute("employee");
        List<EmployeeResponse> liste = new ArrayList<>();
        for (Long employee : listEmpl){
            EmployeeResponse e = employeeService.findById(employee);
            liste.add(e);
        }
        return liste;
    }

    @PostMapping(value = "/booking")
    public String createBooking(@RequestBody BookingRequest booking,HttpServletRequest request) {
        try {
            List<Long> listEmpl = (List<Long>) request.getSession().getAttribute("employee");
            BookingReponse bookingReponse = mapper.map(iBookingService.save(booking),BookingReponse.class) ;
            for (Long employee : listEmpl){
                EmployeeResponse e = new EmployeeResponse();
                e = employeeService.findById(employee);
                bookingDetailService.save(new BookingDetailRequest(bookingReponse.getId(),e.getId(),e.getName(),e.getImage()));
            }
        }catch (Exception e){
            e.getMessage();
        }
        return "Thanh cong";

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


    @GetMapping(value = "/bookingByIdRoomToday/{id}")
    public List<BookingReponse> getByIdRoomDay(@PathVariable("id") long id) {
        return iBookingService.getBookingByRoomDay(id);
    }


}