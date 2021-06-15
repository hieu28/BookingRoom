package com.example.services;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;

public interface IRoomService {
    public RoomResponse save(RoomRequest room);
    public RoomResponse findById(long id);
}
