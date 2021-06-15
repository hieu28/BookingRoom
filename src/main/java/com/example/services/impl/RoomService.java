package com.example.services.impl;

import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomRequest;
import com.example.models.responses.RoomResponse;
import com.example.repositories.RoomRepository;
import com.example.services.IRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public RoomResponse save(RoomRequest room) {
        RoomEntity roomEntity = mapper.map(room, RoomEntity.class);
        roomEntity = roomRepository.save(roomEntity);
        return mapper.map(roomEntity, RoomResponse.class);
    }

    @Override
    public RoomResponse findById(long id) {
        Optional<RoomEntity> entity = roomRepository.findById(id);
        return mapper.map(entity,RoomResponse.class);
    }


}
