package com.example.services.impl;

import com.example.models.entities.RoleEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.RoleResponse;
import com.example.repositories.RoleRepository;
import com.example.services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RoleResponse save(EmployeeRequest role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity = modelMapper.map(role, RoleEntity.class);
        roleEntity = roleRepository.save(roleEntity);
        return modelMapper.map(roleEntity, RoleResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        try {
            roleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public RoleResponse findById(Long id) {
        Optional<RoleEntity> entity = roleRepository.findById(id);
        return  modelMapper.map(entity, RoleResponse.class);
    }
    @Override
    public List<RoleResponse> findAll() {
        List<RoleResponse> result = new ArrayList<>();
        List<RoleEntity> entities = roleRepository.findAll();
        for (RoleEntity item: entities) {
            RoleResponse room = modelMapper.map(result, RoleResponse.class);
            result.add(room);
        }
        return result;
    }
}
