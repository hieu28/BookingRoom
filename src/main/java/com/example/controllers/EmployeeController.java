package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeFageResponse;
import com.example.models.responses.EmployeeResponse;
import com.example.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationNotFoundException;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping
@Validated
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    //Put out employee

    /**
     * Put out employee
     *
     * @return
     */
    @GetMapping("/employee")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.findAll();
    }

    //Put out employee by id
    @GetMapping("/employee/{id}")
    public EmployeeResponse getById(
            @PathVariable("id") long id)
            throws RelationNotFoundException {
        return employeeService.findById(id);
    }


    //More employee
    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employee) {
        return employeeService.save(employee);
    }

    //Repair employee by id
    @PutMapping("/employee/{id}")
    public EmployeeResponse updateEmployee(
            @RequestBody EmployeeRequest model,
            @PathVariable("id") long id) {
        model.setId(id);
        return employeeService.save(model);
    }

    //Delete employee
    @DeleteMapping("/employee")
    public void deleteNew(
            @RequestBody long[] ids) {
        employeeService.deleteList(ids);
    }

    //Delete employee by id
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(
            @PathVariable("id") long id) {
        employeeService.delete(id);
    }

    //Search by email
    @GetMapping("/employee/list/{email}")
    public List<EmployeeResponse> search(
            @PathVariable("email") String email) {

        return employeeService.findByEmail(email);
    }

    //Paging
    @GetMapping("/employee/{page}/{limit}")
    public EmployeeFageResponse ShowPaging(@Min(1) @PathVariable("page") int page,
                                           @Min(1) @PathVariable(name = "limit") int limit) {
        EmployeeFageResponse result = new EmployeeFageResponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListresult(employeeService.findAllPaging(pageable));
        result.setTotalpage((int) Math.ceil((double) (employeeService.totalItem()) / limit));
        return result;
    }

}
