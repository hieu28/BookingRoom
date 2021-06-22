package com.example.services.impl;

import com.example.models.entities.DepartmentEntity;
import com.example.models.responses.DepartmentReponse;
import com.example.repositories.DeprtmentRepository;
import com.example.services.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DeprtmentRepository departmentRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public DepartmentReponse save(DepartmentReponse departmentReponse) {
        DepartmentEntity departmentEntity = mapper.map(departmentReponse, DepartmentEntity.class);
        departmentEntity = departmentRepository.save(departmentEntity);
        return mapper.map(departmentEntity, DepartmentReponse.class);
    }
    @Override
    public void deleteList(long[] ids) {
        // validate id có tồn tại hay không

        for(long item: ids) {
            departmentRepository.deleteById(item);
        }
    }
    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    @Override
    public DepartmentReponse getDeparmentById(Long id) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity = departmentRepository.findOneById(id);
        return mapper.map(departmentEntity, DepartmentReponse.class);
    }
    @Override
    public List<DepartmentReponse> findAllPaging(Pageable pageable) {
        List<DepartmentReponse> results = new ArrayList<>();
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll(pageable).getContent();
        for (DepartmentEntity item: departmentEntities){
            DepartmentReponse departmentReponse = mapper.map(item,DepartmentReponse.class);
            results.add(departmentReponse);
        }
        return results;
    }
    @Override
    public int totallItem() {
        return (int) departmentRepository.count();
    }
}
