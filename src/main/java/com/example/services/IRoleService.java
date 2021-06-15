package com.example.services;

import com.example.models.requests.EmployeeRequest;
import com.example.models.requests.RoleRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.models.responses.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse save(RoleRequest role);
    boolean delete(Long id);
    RoleResponse findById(Long id);
    List<RoleResponse> findAll();

}
