package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.repositories.EmployeeRepository;
import com.example.services.IEmployeeService;
import com.example.services.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

//    @Autowired
//    EmployeeRepository employeeRepository;

//    @GetMapping("/employee")
//    public List<EmployeeEntity> getAllEmployee() {
//        return this.employeeRepository.findAll();
//    }
//
//    @GetMapping("/employee/{id}")
//    public Optional<EmployeeEntity> getById(@PathVariable("id") long id){
//        return employeeRepository.findById(id);
//    }

    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest entity){
       return employeeService.save(entity);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequest model , @PathVariable("id") long id){
        model.setId(id);
        return employeeService.save(model);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") long id){
        employeeService.delete(id);
    }

}
