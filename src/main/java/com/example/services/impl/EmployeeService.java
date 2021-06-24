package com.example.services.impl;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.repositories.DeprtmentRepository;
import com.example.repositories.EmployeeRepository;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public EmployeeResponse save(EmployeeRequest employee) {

        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);

        return modelMapper.map(employeeEntity, EmployeeResponse.class);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public EmployeeResponse findById(Long id) {

        Optional<EmployeeEntity> room = employeeRepository.findById(id);
        return modelMapper.map(room.get(), EmployeeResponse.class);
    }

    @Override
    @Transactional
    public List<EmployeeResponse> findAll() {

        List<EmployeeResponse> r = new ArrayList<>();
        List<EmployeeEntity> entities = employeeRepository.findAll();
        for (EmployeeEntity item : entities) {
            EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
            r.add(employee);
        }
        return r;
    }

    @Override
    @Transactional
    public List<EmployeeResponse> findByEmail(String email) {

        try {
            List<EmployeeEntity> epl = employeeRepository.search(email);
            List<EmployeeResponse> eo = new ArrayList<>();
            for (EmployeeEntity item : epl) {
                EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
                eo.add(employee);
            }
            return eo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
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

    @Override
    public void deleteList(long[] ids) {

        for (long item : ids) {
            employeeRepository.deleteById(item);
        }
    }

}








