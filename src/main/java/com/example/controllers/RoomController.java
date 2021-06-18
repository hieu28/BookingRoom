package com.example.controllers;

import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;
import com.example.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @GetMapping(value = "/room")
    public List<RoomResponse> getAll(){
        return roomService.findAll();
    }

    @GetMapping(value = "/room/{id}")
    public RoomResponse getById(@PathVariable("id") long id){
        return roomService.findById(id);
    }

    @GetMapping(value = "/room/list/{id}")
    public List<RoomResponse> getByLocation(@PathVariable("id") long id){
        return  roomService.findByLocation(id);
    }


    @PostMapping(value = "/room")
    public RoomResponse createRoom(@RequestBody RoomRequest room) {
        RoomResponse rooms = roomService.save(room);
        return rooms;
    }

    @PutMapping(value = "/room/{id}")
    public RoomResponse updateNew(@RequestBody RoomRequest model, @PathVariable("id") long id) {
        model.setId(id);
        return roomService.save(model);
    }
    @DeleteMapping(value = "/room/{id}")
    public void deleteNew(@PathVariable("id") long ids) {
        roomService.delete(ids);
    }
}
