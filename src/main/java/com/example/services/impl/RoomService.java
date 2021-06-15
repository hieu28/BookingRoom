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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomResponse> findAll() {
        List<RoomResponse> result = new ArrayList<>();
        List<RoomEntity> entities = roomRepository.findAll();
        for (RoomEntity item: entities) {
            RoomResponse roomDTO = mapper.map(result, RoomResponse.class);
            result.add(roomDTO);
        }
        return result;
    }



    @Override
    @Transactional
    public RoomResponse save(RoomRequest r) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity = mapper.map(r, RoomEntity.class);
        roomEntity = roomRepository.save(roomEntity);
        return mapper.map(roomEntity, RoomResponse.class);
    }

    @Override
    @Transactional
    public boolean delete( long ids) {
        try {
            roomRepository.deleteById(ids);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }


}
