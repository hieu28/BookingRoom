package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> getById(@PathVariable("id") long id) throws RelationNotFoundException {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

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
