package com.example.services;

import com.example.models.responses.DepartmentReponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    DepartmentReponse save(DepartmentReponse departmentReponse);

    void deleteList(long[] ids);

    void deleteDepartment(Long id);

    DepartmentReponse getDeparmentById(Long id);

    int totallItem();

    List<DepartmentReponse> findAllPaging(Pageable pageable);
}
