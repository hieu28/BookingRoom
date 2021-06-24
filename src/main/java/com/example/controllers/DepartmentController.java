package com.example.controllers;

import com.example.models.entities.DepartmentEntity;
import com.example.models.responses.DepartmentReponse;
import com.example.models.responses.DepratmentFindAllPagReponse;
import com.example.repositories.DeprtmentRepository;
import com.example.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DepartmentController {
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private DeprtmentRepository deprtmentRepository;

    @PostMapping(value = "/department")
    public DepartmentReponse createDepartment(@RequestBody DepartmentReponse model) {
        return iDepartmentService.save(model);
    }

    @PutMapping(value = "/department/{id}")
    public DepartmentReponse updateDepartment(@RequestBody DepartmentReponse model, @PathVariable("id") long id) {
        model.setId(id);
        return iDepartmentService.save(model);
    }

    @DeleteMapping(value = "/department")
    public void deleteListDepartment(@RequestBody long[] ids) {
        iDepartmentService.deleteList(ids);
    }

    @DeleteMapping(value = "/department/{id}")
    public String deleteDepartment(@PathVariable("id") long id) {
        iDepartmentService.deleteDepartment(id);
        return "xóa thành công";
    }

    @GetMapping(value = "/department/{id}")

    public DepartmentReponse getById(@PathVariable("id") long id) {

        return iDepartmentService.getDeparmentById(id);
    }

    @GetMapping(value = "/department")
    public DepratmentFindAllPagReponse ShowDepartmentPaging(@RequestParam("page") int page,
                                                            @RequestParam("limit") int limit) {
        DepratmentFindAllPagReponse result = new DepratmentFindAllPagReponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListresult(iDepartmentService.findAllPaging(pageable));
        result.setTotalpage((int) Math.ceil((double) (iDepartmentService.totallItem()) / limit));
        return result;
    }

    @GetMapping(value = "/department/getall")
    public List<DepartmentEntity> ShowBookingAll() {

        return this.deprtmentRepository.findAll();
    }
}
