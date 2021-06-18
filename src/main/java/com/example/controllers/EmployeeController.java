package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeFageResponse;
import com.example.models.responses.EmployeeResponse;
import com.example.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/employee")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> getById(@PathVariable("id") long id) throws RelationNotFoundException {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }


    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest entity){
       return employeeService.save(entity);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequest model , @PathVariable("id") long id){
        model.setId(id);
        return employeeService.save(model);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") long id){
        employeeService.delete(id);
    }

    @GetMapping("/employee/list/{email}")
    public List<EmployeeResponse> search(@PathVariable("email") String email){

        return employeeService.findByEmail(email);
    }
    @GetMapping(value = "/employee")
    public EmployeeFageResponse ShowPaging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        EmployeeFageResponse result = new EmployeeFageResponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page-1,limit);
        result.setListresult(employeeService.findAllPaging(pageable));
        result.setTotalpage((int)Math.ceil((double)(employeeService.totalItem())/limit));
        return result;
    }
}
