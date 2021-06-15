package com.example.repositories;

import com.example.models.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeprtmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findOneById(Long id);
}
