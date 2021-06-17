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
//    @Override
//    public List<RoomFindAllIndex> finAllRoom() {
//        List<RoomFindAllIndex> results = new ArrayList<>();
//        List<LocationEntity> locationEntities = locationRepository.findAllLocation();
//        List<Long> listIdLocation = new ArrayList<>();
//        for(LocationEntity item: locationEntities) {
//            listIdLocation.add(item.getId());
//        }
//        RoomFindAllIndex roomFindAllIndexx = new RoomFindAllIndex();
//        for(long item: listIdLocation) {
//            LocationEntity lce = locationRepository.findOneById(item);
//            RoomEntity roomEntity = roomRepository.findOneByIdLocationId(item);
//            RoomFindAllIndex roomFinda = mapper.map(roomEntity,RoomFindAllIndex.class);
//            roomFindAllIndexx.setNameLocation(lce.getName());
//            roomFindAllIndexx.setName(roomEntity.getName());
//            roomFindAllIndexx.setCapacity(roomEntity.getCapacity());
//            roomFindAllIndexx.setId(roomEntity.getId());
//            roomFindAllIndexx.setImage(roomEntity.getImage());
//            roomFindAllIndexx.setLocationId(roomEntity.getLocationId());
//            results.add(roomFinda);
//        }
//        return results;
//    }
    @Override
    public List<RoomFindAllIndex> findAllRoomById(Long id) {
        List<RoomFindAllIndex> resultss = new ArrayList<>();
        List<RoomEntity> roomEntities = roomRepository.findAllByLocationId(id);
        RoomFindAllIndex roomFindAllIndex = new RoomFindAllIndex();
        for (RoomEntity item: roomEntities){
            LocationEntity locationEntities = locationRepository.findOneById(item.getLocationId());
            RoomFindAllIndex roomFind = mapper.map(item,RoomFindAllIndex.class);
            roomFindAllIndex.setNameLocation(locationEntities.getName());
            roomFindAllIndex.setName(item.getName());
            roomFindAllIndex.setCapacity(item.getCapacity());
            roomFindAllIndex.setId(item.getId());
            roomFindAllIndex.setImage(item.getImage());
            roomFindAllIndex.setLocationId(item.getLocationId());
            resultss.add(roomFindAllIndex);
        }
        return resultss;
    }
}
