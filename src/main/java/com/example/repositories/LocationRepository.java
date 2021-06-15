package com.example.repositories;

import com.example.models.entities.LocationEntity;
import com.example.models.requests.LocationCreatedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
//    LocationCreatedRequest save(LocationCreatedRequest locationCreatedRequest);
}
