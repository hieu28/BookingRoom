package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.repositories.EmployeeRepository;
import com.example.services.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public List<EmployeeEntity> getAllEmployee() {
        return this.employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity entity){
        return employeeRepository.save(entity);
    }


}
