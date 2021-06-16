package com.example.services.impl;

import com.example.models.entities.BookingEntity;
import com.example.models.entities.LocationEntity;
import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomRequest;
import com.example.models.responses.BookingReponse;
import com.example.models.responses.RoomFindAllIndex;
import com.example.models.responses.RoomResponse;
import com.example.repositories.LocationRepository;
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
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<RoomResponse> findAll() {
        List<RoomResponse> result = new ArrayList<>();
        List<RoomEntity> entities = roomRepository.findAll();
        for (RoomEntity item: entities) {
            RoomResponse roomDTO = mapper.map(item, RoomResponse.class);
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
    @Override
    public RoomResponse findById(long id) {
        Optional<RoomEntity> entity = roomRepository.findById(id);
        RoomResponse room = mapper.map(entity.get(),RoomResponse.class);
        return room;
    }

}
