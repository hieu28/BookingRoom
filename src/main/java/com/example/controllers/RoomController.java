package com.example.controllers;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;
import com.example.services.IRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(value = "/room")
    public void createRoom(@RequestBody RoomRequest room) {
        roomService.save(room);
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
