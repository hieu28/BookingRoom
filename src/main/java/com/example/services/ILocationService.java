package com.example.services;

import com.example.models.requests.LocationCreatedRequest;
import com.example.models.responses.LocationCreatedResponse;

import java.util.List;

public interface ILocationService {

    LocationCreatedResponse save(LocationCreatedResponse locationCreatedRequest);
//    void deleteLocation(Long id);
//    LocationCreatedRequest updateLocation(Long id, LocationCreatedRequest location);
////    LocationCreatedRequest createLocation(LocationCreatedRequest location);
//    LocationCreatedResponse findById(Long id);
//    List<LocationCreatedResponse> getAll();
}
