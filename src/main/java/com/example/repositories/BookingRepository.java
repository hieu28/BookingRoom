package com.example.repositories;

import com.example.models.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    BookingEntity findOneById(Long id);
    @Query("SELECT r from BookingEntity r where r.employeeId=?1")
    List<BookingEntity> findAllByIdE(Long employeeId);
    @Query("SELECT r from BookingEntity r where r.roomId=?1")
    List<BookingEntity> findAllByIdRoom(Long roomId);
}

