package com.example.services;

import com.example.models.requests.EmployeeBookingRequest;
import com.example.models.responses.EmployeeBookingResponse;

import java.util.List;

public interface IEmployeeBookingService {
    public List<EmployeeBookingResponse> findAll();
    public EmployeeBookingResponse save(EmployeeBookingRequest employeeBookingResponse);
    public void delete(long id);
    public EmployeeBookingResponse findById(long id);
}
