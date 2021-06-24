package com.example.services.impl;

import com.example.exceptions.DuplicateRecordException;
import com.example.exceptions.NotFoundException;
import com.example.mappers.RoomMapper;
import com.example.models.entities.LocationEntity;
import com.example.models.entities.RoomEntity;
import com.example.models.requests.RoomCreatedRequest;
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
        room.forEach(
                e -> {
                    RoomResponse roomDTO = mapper.map(e, RoomResponse.class);
                    result.add(roomDTO);
                }
        );
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


    @Override
    @Transactional
    public RoomResponse create(RoomCreatedRequest room) {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        roomEntities.forEach(
                e->{
                    if (e.getName().equals(room.getName())){
                        throw new DuplicateRecordException("Phòng đã tồn tại trong hệ thống");
                    }
                }
        );
        RoomEntity roomEntity = mapper.map(room, RoomEntity.class);
        roomEntity = roomRepository.save(roomEntity);
        return mapper.map(roomEntity, RoomResponse.class);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        roomRepository.deleteById(id);
        return true;
    }

    @Override
    public RoomResponse getById(long id) {
        Optional<RoomEntity> room = roomRepository.findById(id);
        if (room.get().getId() != id) {
            throw new NotFoundException("Room ko ton tai");
        }
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
