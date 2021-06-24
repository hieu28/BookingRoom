package com.example.services.impl;

import com.example.exceptions.DuplicateRecordException;
import com.example.models.entities.DepartmentEntity;
import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.repositories.DeprtmentRepository;
import com.example.repositories.EmployeeRepository;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements IEmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DeprtmentRepository deprtmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponse create(EmployeeRequest employee) {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        for (EmployeeEntity emp : employeeEntity) {
            if (emp.getEmail().equals(employee.getEmail())) {
                throw new DuplicateRecordException("Email already exists in the system");
            }
        }
        EmployeeEntity em = modelMapper.map(employee, EmployeeEntity.class);
        em.setPassword(passwordEncoder.encode("123456a@"));
        em = employeeRepository.save(em);
        return modelMapper.map(em, EmployeeResponse.class);
    }

    @Override
    public boolean delete(Long id) {

        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmployeeResponse findById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return modelMapper.map(employeeEntity.get(), EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> findAll() {

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        for (EmployeeEntity item : employeeEntity) {
            EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
            employeeResponses.add(employee);
        }
        List<DepartmentEntity> departmentEntity = deprtmentRepository.findAll();
        for (EmployeeResponse employeeResponse : employeeResponses) {
            for (DepartmentEntity item : departmentEntity) {
                if (employeeResponse.getDepartmentId() == item.getId()) {
                    employeeResponse.setDepartmentName(item.getName());
                }
            }
        }
        return employeeResponses;
    }

    @Override
    public List<EmployeeResponse> searchEmail(String email) {
        try {
            List<EmployeeEntity> employeeEntity = employeeRepository.search(email);
            List<EmployeeResponse> employeeResponse = new ArrayList<>();
            for (EmployeeEntity item : employeeEntity) {
                EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
                employeeResponse.add(employee);
            }
            return employeeResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeResponse> findAllPaging(Pageable pageable) {

        List<EmployeeResponse> results = new ArrayList<>();
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll(pageable).getContent();
        for (EmployeeEntity item : employeeEntity) {
            EmployeeResponse employeeResponse = modelMapper.map(item, EmployeeResponse.class);
            results.add(employeeResponse);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) employeeRepository.count();
    }


}








