package com.example.services;

import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    EmployeeResponse save(EmployeeRequest employee);
    boolean delete(Long id);
    EmployeeResponse findById(Long id);
    List<EmployeeResponse> findAll();


}
