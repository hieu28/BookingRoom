package com.example.repositories;

import com.example.models.entities.EmployeeBookingEntity;

import com.example.models.responses.EmployeeBookingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeBookingRepository extends JpaRepository<EmployeeBookingEntity,Long> {
//    Optional<EmployeeBookingEntity> findByEmployeeName(String name);

//    @Query(value = "SELECT * FROM employee  WHERE name LIKE '%'  :keyword '%'",nativeQuery = true)
//    List<EmployeeBookingResponse> search(@Param("keyword") String keyword);

    @Query("SELECT r from EmployeeBookingEntity r where r.employeeId=?1")
    List<EmployeeBookingEntity> findAllByIdB(Long bookingId);
}
