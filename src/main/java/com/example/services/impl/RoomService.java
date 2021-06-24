package com.example.services.impl;

import com.example.models.entities.LocationEntity;
import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomRequest;
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
    public List<RoomResponse> getAll() {
        List<RoomEntity> room = roomRepository.findAll();
        List<RoomResponse> result = new ArrayList<>();
        room.stream().forEach(
                e -> {
                    RoomResponse roomDTO = mapper.map(e, RoomResponse.class);
                    result.add(roomDTO);
                }
        );
        List<LocationEntity> location = locationRepository.findAll();
        for (RoomResponse roomResponse : result) {
            for (LocationEntity item : location) {
                if (roomResponse.getLocationId() == item.getId()) {
                    roomResponse.setLocationName(item.getName());
                }
            }
        }
        return result;
    }


    @Override
    @Transactional
    public RoomResponse create(RoomRequest room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity = mapper.map(room, RoomEntity.class);
        roomEntity = roomRepository.save(roomEntity);
        return mapper.map(roomEntity, RoomResponse.class);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        roomRepository.deleteAllByIdIn(ids);

    }

    @Override
    public RoomResponse getById(long id) {
        Optional<RoomEntity> room = roomRepository.findById(id);
        return mapper.map(room.get(), RoomResponse.class);
    }

    @Override
    public List<RoomResponse> getByLocation(long id) {
        Optional<List<RoomEntity>> room = roomRepository.findByLocationId(id);
        List<RoomResponse> result = new ArrayList<>();
        for (RoomEntity item : room.get()) {
            RoomResponse roomDTO = mapper.map(item, RoomResponse.class);
            result.add(roomDTO);
        }
        List<LocationEntity> location = locationRepository.findAll();
        for (RoomResponse roomResponse : result) {
            for (LocationEntity item : location) {
                if (roomResponse.getLocationId().equals(item.getId())) {
                    roomResponse.setLocationName(item.getName());
                }
            }
        }
        return result;
    }

}
