package com.example.services.impl;

import com.example.mappers.ModelMapperConfig;
import com.example.models.entities.LocationEntity;
import com.example.models.requests.LocationCreatedRequest;
import com.example.models.responses.LocationCreatedResponse;
import com.example.repositories.LocationRepository;
import com.example.services.ILocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LocationCreatedResponse save(LocationCreatedResponse locationCreatedResponse) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity = modelMapper.map(locationCreatedResponse, LocationEntity.class);
        locationEntity = locationRepository.save(locationEntity);
        return modelMapper.map(locationEntity, LocationCreatedResponse.class);
    }



//    @Override
//    public List<LocationCreatedResponse> getAll() {
//        return null;
//    }
}
