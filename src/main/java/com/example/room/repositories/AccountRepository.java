package com.example.room.repositories;

import com.example.room.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}
