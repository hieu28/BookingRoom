package com.example.services;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeResponse;
import org.springframework.data.domain.Pageable;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

public interface IEmployeeService {
    EmployeeResponse save(EmployeeRequest employee);
    boolean delete(Long id);
    EmployeeResponse findById(Long id);
    List<EmployeeResponse> findAll();
    List<EmployeeResponse> findByEmail(String email);
    List<EmployeeResponse> findAllPaging(Pageable pageable);

    int totalItem();
    void deleteList(long[] ids);

}
