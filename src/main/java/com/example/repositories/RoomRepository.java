package com.example.repositories;

import com.example.models.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    Optional<RoomEntity> findOneById(Long id);

}
