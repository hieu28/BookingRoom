package com.example.controllers;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;
import com.example.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @PostMapping(value = "/room")
    public void createRoom(@RequestBody RoomRequest room) {
        roomService.save(room);
    }

    @GetMapping(value = "/room/{id}")
    public RoomResponse initRoom( @PathVariable("id") long id) {
         RoomResponse room = roomService.findById(id);
         return room;
    }
//
//    @DeleteMapping(value = "/room/{id}")
//    public void deleteNew(@PathVariable("id") long ids) {
//        roomService.delete(ids);
//    }
}
