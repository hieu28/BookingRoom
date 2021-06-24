package com.example.repositories;

import com.example.models.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT * FROM employee  WHERE email LIKE '%'  :keyword '%'", nativeQuery = true)
    List<EmployeeEntity> search(@Param("keyword") String keyword);

    Optional<EmployeeEntity> findByEmail(String email);

}
