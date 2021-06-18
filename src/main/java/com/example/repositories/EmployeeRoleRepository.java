package com.example.repositories;

import com.example.models.entities.EmployeeRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleEntity, Long> {
}
