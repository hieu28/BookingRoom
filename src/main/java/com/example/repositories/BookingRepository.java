package com.example.repositories;

import com.example.models.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Booking findOneById(Long id);
    Booking findAllByIdEmployee(Long employeeId);
}
