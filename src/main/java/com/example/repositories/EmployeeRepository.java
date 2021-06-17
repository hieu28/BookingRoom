package com.example.repositories;

import com.example.models.entities.EmployeeEntity;
import com.example.models.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllById(Long id);
    EmployeeEntity findOneById(Long id);
}
