package com.example.services.impl;

import com.example.models.entities.LocationEntity;
import com.example.models.responses.LocationResponse;
import com.example.repositories.LocationRepository;
import com.example.services.ILocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public LocationResponse save(LocationResponse locationResponse) {
        LocationEntity  locationEntity;
        locationEntity = mapper.map(locationResponse, LocationEntity.class);
        locationEntity = locationRepository.save(locationEntity);
        return mapper.map(locationEntity, LocationResponse.class);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public LocationResponse getLocationById(Long id) {
        LocationEntity locationEntity;
        locationEntity = locationRepository.findOneById(id);
        return mapper.map(locationEntity, LocationResponse.class);
    }

    @Override
    public List<LocationResponse> findAllLocation() {
        List<LocationResponse> resultss = new ArrayList<>();
        List<LocationEntity> locationEntities = locationRepository.findAll();
        for (LocationEntity item: locationEntities){
            LocationResponse locationResponse = mapper.map(item,LocationResponse.class);
            resultss.add(locationResponse);
        }
        return resultss;
    }

}
