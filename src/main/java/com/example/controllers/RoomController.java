package com.example.controllers;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;
import com.example.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @GetMapping
    public List<RoomResponse> getAll() {
        return roomService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RoomResponse getById(@PathVariable("id") long id) {
        return roomService.getById(id);
    }

    @GetMapping(value = "/location/{id}")
    public List<RoomResponse> getByLocation(@PathVariable("id") long id) {
        return roomService.getByLocation(id);
    }

    @PostMapping
    public RoomResponse create(@RequestBody RoomRequest roomRequest) {
        return roomService.create(roomRequest);
    }

    @PutMapping(value = "/{id}")
    public RoomResponse update(@PathVariable("id") long id, @RequestBody RoomRequest roomRequest) {
        roomRequest.setId(id);
        return roomService.create(roomRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody List<Long> ids) {
        roomService.delete(ids);
    }
}
