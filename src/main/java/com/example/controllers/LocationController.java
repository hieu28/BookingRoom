package com.example.controllers;

import com.example.models.requests.LocationRequest;
import com.example.models.responses.LocationResponse;
import com.example.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private ILocationService iLocationService;

    @PostMapping("/location")
    public LocationResponse createLocation(@RequestBody LocationResponse locationResponse) {
        return iLocationService.save(locationResponse);
    }

    @PutMapping(value = "/location/{id}")
    public LocationResponse updateLocation(@RequestBody LocationResponse model, @PathVariable("id") long id) {
        model.setId(id);
        return iLocationService.save(model);
    }

    //    @DeleteMapping(value = "/location")
//    public void deleteLocation(@RequestBody long ids) {
//        iLocationService.deleteLocation(ids);
//    }
    @DeleteMapping(value = "/location/{id}")
    public void deleteLocation(@PathVariable("id") long id) {
        iLocationService.deleteLocation(id);
    }

    @GetMapping(value = "/location/{id}")

    public LocationResponse getById(@PathVariable("id") long id) {

        return iLocationService.getLocationById(id);
    }

    @GetMapping(value = "/location")
    public List<LocationResponse> findAdd() {

        return iLocationService.findAllLocation();
    }
}
