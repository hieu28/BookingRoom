package com.example.controllers;

import com.example.models.requests.EmployeeBookingRequest;
import com.example.models.responses.EmployeeBookingResponse;
import com.example.models.responses.EmployeeResponse;
import com.example.services.IEmployeeBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeBookingController {
    @Autowired
    IEmployeeBookingService employeeBookingService;

    @GetMapping("/employeeBooking")
    public List<EmployeeBookingResponse> getAllEmployeeBooking() {
        return employeeBookingService.findAll();
    }

    @GetMapping("/employeeBooking/{id}")
    public EmployeeBookingResponse getById(@PathVariable("id") long id) {
        return employeeBookingService.findById(id);
    }


    @PostMapping("/employeeBooking")
    public EmployeeBookingResponse createEmployeeBooking(@RequestBody EmployeeBookingRequest entity){
        return employeeBookingService.save(entity);
    }

    @PutMapping("/employeeBooking/{id}")
    public EmployeeBookingResponse updateEmployeeBooking(@RequestBody EmployeeBookingRequest model , @PathVariable("id") long id){
        model.setId(id);
        return employeeBookingService.save(model);
    }

    @DeleteMapping("/employeeBooking/{id}")
    public void deleteEmployeeBooking(@PathVariable("id") long id){

        employeeBookingService.delete(id);
    }
}
