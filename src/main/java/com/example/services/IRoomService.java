package com.example.services;

import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomFindAllIndex;
import com.example.models.responses.RoomResponse;

import java.util.List;

public interface IRoomService {
    public List<RoomResponse> findAll();
    public RoomResponse save(RoomRequest room);
    public boolean delete(long id);
    List<RoomFindAllIndex> findAllRoomById(Long id);
}
