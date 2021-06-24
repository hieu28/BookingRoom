package com.example.services;

import com.example.models.requests.RoomCreatedRequest;
import com.example.models.responses.RoomResponse;

import java.util.List;

public interface IRoomService {

    List<RoomResponse> getAll();

    RoomResponse create(RoomCreatedRequest room);

    RoomResponse getById(long id);

    List<RoomResponse> getByLocation(long id);

    boolean delete(Long id);
}
