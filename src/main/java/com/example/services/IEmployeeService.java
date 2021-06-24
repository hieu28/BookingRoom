package com.example.services;

import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {

    EmployeeResponse create(EmployeeRequest employee);

    boolean delete(Long id);

    EmployeeResponse findById(Long id);

    List<EmployeeResponse> findAll();

    List<EmployeeResponse> searchEmail(String email);

    List<EmployeeResponse> findAllPaging(Pageable pageable);

    int totalItem();

}
