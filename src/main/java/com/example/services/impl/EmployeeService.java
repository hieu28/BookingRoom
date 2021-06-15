package com.example.services.impl;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.repositories.EmployeeRepository;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public EmployeeRequest save(EmployeeRequest employee) {
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeRequest.class);
    }


    @Override
    public EmployeeRequest findById(long id) {
        return null;
    }


    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }


}
