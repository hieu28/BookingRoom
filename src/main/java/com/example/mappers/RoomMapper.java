package com.example.mappers;

import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomCreatedRequest;
import com.example.models.responses.RoomResponse;

public class RoomMapper {
    public static RoomResponse toRoomResponse(RoomEntity room) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(room.getId());
        roomResponse.setName(room.getName());
        roomResponse.setLocationId(room.getLocationId());
        roomResponse.setCapacity(room.getCapacity());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setImage(room.getImage());
        roomResponse.setStatus(room.isStatus());
        return roomResponse;
    }

    public static RoomEntity toRoomEntity(RoomCreatedRequest req) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setName(req.getName());
        roomEntity.setLocationId(req.getLocationId());
        roomEntity.setCapacity(req.getCapacity());
        roomEntity.setDescription(req.getDescription());
        roomEntity.setImage(req.getImage());
        roomEntity.setStatus(req.isStatus());
        return roomEntity;
    }
}
