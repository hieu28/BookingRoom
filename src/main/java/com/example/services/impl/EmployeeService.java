package com.example.services.impl;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.repositories.EmployeeRepository;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public EmployeeResponse save(EmployeeRequest employee) {
        EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        employeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public EmployeeEntity findById(Long id) throws RelationNotFoundException {
        Optional<EmployeeEntity> entity = this.employeeRepository.findById(id);
        if (entity.isPresent()){
            return entity.get();
        }else{
            throw new RelationNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public List<EmployeeResponse> findAll() {
        List<EmployeeResponse> r = new ArrayList<>();
        List<EmployeeEntity> entities = employeeRepository.findAll();
        for (EmployeeEntity item: entities) {
            EmployeeResponse roomDTO = modelMapper.map(item, EmployeeResponse.class);
            r.add(roomDTO);
        }
        return r;
    }

}
