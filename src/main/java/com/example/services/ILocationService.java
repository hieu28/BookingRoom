package com.example.services;

import com.example.models.responses.LocationResponse;

import java.util.List;

public interface ILocationService {
    LocationResponse save(LocationResponse locationResponse);

    void deleteLocation(Long id);

    LocationResponse getLocationById(Long id);

    List<LocationResponse> findAllLocation();
}
