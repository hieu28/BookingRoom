package com.example.repositories;

import com.example.models.entities.BookingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetailEntity,Long> {
    @Query("SELECT r from BookingDetailEntity r where r.employeeId=?1")
    List<BookingDetailEntity> findAllByIdB(Long bookingId);
}
