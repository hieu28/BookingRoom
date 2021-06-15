package com.example.repositories;

import com.example.models.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    BookingEntity findOneById(Long id);
}
