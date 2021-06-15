package com.example.repositories;

import com.example.models.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.net.ContentHandler;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
    LocationEntity findOneById(Long id);

}