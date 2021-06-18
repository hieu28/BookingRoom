package com.example.repositories;

import com.example.models.entities.EmployeeBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeBookingRepository extends JpaRepository<EmployeeBookingEntity,Long> {
    @Query("SELECT r from EmployeeBookingEntity r where r.employeeId=?1")
    List<EmployeeBookingEntity> findAllByIdB(Long bookingId);
}
