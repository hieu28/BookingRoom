package com.example.controllers;

import com.example.mappers.ModelMapperConfig;
import com.example.models.entities.LocationEntity;
import com.example.models.requests.LocationCreatedRequest;
import com.example.models.responses.LocationCreatedResponse;
import com.example.repositories.LocationRepository;
import com.example.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ILocationService locationService;
//
//    @Autowired
//    private ModelMapperConfig modelMapperConfig;

    @GetMapping("/location")
    public List<LocationEntity> findAll() {
        return locationRepository.findAll();
    }
//
//    @GetMapping("/location/{id}")
//    public Optional<LocationEntity> getLocationById(@PathVariable("id") Long id) {
//        return locationRepository.findById(id);
//    }

    @PostMapping("/location")
    public LocationCreatedResponse createLocation(@Validated @RequestBody LocationCreatedResponse locationCreatedRequest) {
        return locationService.save(locationCreatedRequest); }

//    @PutMapping("/location/{id}")
//    public void updateLocation(@PathVariable Long id, @RequestBody LocationCreatedRequest locationGreatedRequest) {
//        locationRepository.updateLocation(id, locationGreatedRequest);
//    }
//
//    @DeleteMapping("/location/{id}")
//    public void deleteLocation(@PathVariable Long id) {
//        locationRepository.deleteLocation(id);
//    }
}
