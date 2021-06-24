package com.example.controllers;

import com.example.models.requests.RoomCreatedRequest;
import com.example.models.responses.RoomResponse;
import com.example.services.IRoomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get list room", response = RoomResponse.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<RoomResponse> result = roomService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
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
    public RoomResponse create(@RequestBody RoomCreatedRequest roomRequest) {
        return roomService.create(roomRequest);
    }

    @PutMapping(value = "/{id}")
    public RoomResponse update(@PathVariable("id") long id, @RequestBody RoomCreatedRequest roomRequest) {
//        roomRequest.setId(id);
        return roomService.create(roomRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        roomService.delete(id);
    }
}
