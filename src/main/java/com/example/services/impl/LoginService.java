package com.example.services.impl;

import com.example.exceptions.PasswordNotFoundException;
import com.example.exceptions.UsernameNotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public LoginService(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public EmployeeEntity checkUser(String email, String password) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmail(email);
        if (!employeeOptional.isPresent()) {
            throw new UsernameNotFound();
        }
        if (!password.equals(employeeOptional.get().getPassword())) {
            throw new PasswordNotFoundException();
        }
        return employeeOptional.get();


    }

}
