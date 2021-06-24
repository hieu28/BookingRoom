package com.example.services.impl;

import com.example.models.entities.BookingEntity;
import com.example.models.entities.EmployeeBookingEntity;
import com.example.models.entities.EmployeeEntity;
import com.example.models.entities.RoomEntity;
import com.example.models.requests.EmployeeBookingRequest;
import com.example.models.responses.EmployeeBookingResponse;
import com.example.repositories.BookingRepository;
import com.example.repositories.EmployeeBookingRepository;
import com.example.repositories.EmployeeRepository;
import com.example.repositories.RoomRepository;
import com.example.services.IEmployeeBookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeBookingService implements IEmployeeBookingService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmployeeBookingRepository employeeBookingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<EmployeeBookingResponse> findAll() {
        List<EmployeeBookingEntity> employeeBookingEntityList = employeeBookingRepository.findAll();
        List<EmployeeBookingResponse> responses = new ArrayList<>();
        for (EmployeeBookingEntity item : employeeBookingEntityList) {
            EmployeeBookingResponse response = mapper.map(item, EmployeeBookingResponse.class);
            responses.add(response);
        }
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<BookingEntity> booking = bookingRepository.findAll();
        List<RoomEntity> room = roomRepository.findAll();
        for (EmployeeBookingResponse employeeBookingResponse : responses) {
            for (BookingEntity booker : booking) {
                for (RoomEntity roomer : room) {
                    if (employeeBookingResponse.getBookingId() == booker.getId()) {
                        employeeBookingResponse.setRoomName(roomer.getName());
                    }
                }
            }
            for (EmployeeEntity employee : employees) {
                if (employeeBookingResponse.getEmployeeId() == employee.getId()) {
                    employeeBookingResponse.setEmployeeName(employee.getName());
                }
            }
        }
        return responses;
    }

    @Override
    public EmployeeBookingResponse save(EmployeeBookingRequest employeeBookingResponse) {
        EmployeeBookingEntity entity = mapper.map(employeeBookingResponse, EmployeeBookingEntity.class);
        entity = employeeBookingRepository.save(entity);
        return mapper.map(entity, EmployeeBookingResponse.class);
    }

    @Override
    public void delete(long id) {
        employeeBookingRepository.deleteById(id);
    }

    @Override
    public EmployeeBookingResponse findById(long id) {
        Optional<EmployeeBookingEntity> entity = this.employeeBookingRepository.findById(id);
        return mapper.map(entity.get(), EmployeeBookingResponse.class);
    }
}
