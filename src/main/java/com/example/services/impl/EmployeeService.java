package com.example.services.impl;

import com.example.models.entities.DepartmentEntity;
import com.example.models.entities.EmployeeEntity;
import com.example.models.entities.EmployeeRoleEntity;
import com.example.models.entities.RoleEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import com.example.repositories.DeprtmentRepository;
import com.example.repositories.EmployeeRepository;
import com.example.repositories.EmployeeRoleRepository;
import com.example.repositories.RoleRepository;
import com.example.services.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements IEmployeeService {

    public EmployeeEntity findByemail;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DeprtmentRepository deprtmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity findEmployee(String email) {
        return employeeRepository.findByEmail(email).get();
    }

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public EmployeeEntity findById(Long id) throws RelationNotFoundException {
        Optional<EmployeeEntity> entity = this.employeeRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RelationNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public List<EmployeeResponse> findAll() {
        List<EmployeeResponse> r = new ArrayList<>();
        List<EmployeeEntity> entities = employeeRepository.findAll();
        for (EmployeeEntity item : entities) {
            EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
            r.add(employee);
        }
        List<DepartmentEntity> emp = deprtmentRepository.findAll();
        List<RoleEntity> role = roleRepository.findAll();
        List<EmployeeRoleEntity> emplr = employeeRoleRepository.findAll();
        for (EmployeeResponse employeeResponse : r) {
            for (DepartmentEntity item : emp) {
                for (EmployeeRoleEntity employeeRoleEntity : emplr) {
                    for (RoleEntity roleEntity : role) {
                        if (employeeResponse.getDepartmentId()==item.getId()&&employeeResponse.getId()==employeeRoleEntity.getEmployeeId()&&employeeRoleEntity.getRoleId()==roleEntity.getId()) {
                            employeeResponse.setRoleName(roleEntity.getName());
                            employeeResponse.setDepartmentName(item.getName());
                        }
                    }
                }
            }
        }
        return r;
    }


    @Override
    public List<EmployeeResponse> findByEmail(String email) {
        try {
            List<EmployeeEntity> empl = employeeRepository.search(email);
            List<EmployeeResponse> eo = new ArrayList<>();
            for (EmployeeEntity item : empl) {
                EmployeeResponse employee = modelMapper.map(item, EmployeeResponse.class);
                eo.add(employee);
            }
            return eo;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<EmployeeResponse> findAllPaging(Pageable pageable) {
        List<EmployeeResponse> results = new ArrayList<>();
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll(pageable).getContent();
        for (EmployeeEntity item: employeeEntity){
            EmployeeResponse employeeResponse = modelMapper.map(item,EmployeeResponse.class);
            results.add(employeeResponse);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) employeeRepository.count();
    }

}








