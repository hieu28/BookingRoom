package com.example.services;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;

import java.util.List;

public interface IRoomService {

     List<RoomResponse> getAll();

     RoomResponse create(RoomRequest room);

     void delete(Long[] id);

     RoomResponse getById(long id);

     List<RoomResponse> getByLocation(long id);
}
