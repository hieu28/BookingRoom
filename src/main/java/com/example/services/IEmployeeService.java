package com.example.services;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;

import java.util.List;

public interface IEmployeeService {
    EmployeeRequest save(EmployeeRequest employee);
    void delete(long id);
    EmployeeRequest findById(long id);

}
