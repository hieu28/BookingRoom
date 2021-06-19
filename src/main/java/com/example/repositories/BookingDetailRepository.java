package com.example.repositories;

import com.example.models.entities.BookingDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailRepository extends JpaRepository<BookingDetailEntity,Long> {
}
