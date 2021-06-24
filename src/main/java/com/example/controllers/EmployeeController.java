package com.example.controllers;

import com.example.models.requests.EmployeeRequest;
import com.example.models.responses.EmployeeFageResponse;
import com.example.models.responses.EmployeeResponse;
import com.example.services.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * Put out employee
     *
     * @return
     */
    @GetMapping("/employee")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.findAll();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}")
    public EmployeeResponse getById(@PathVariable("id") long id) {
        return employeeService.findById(id);
    }

    /**
     * @param email
     * @return
     */
    @GetMapping("/employee/search/{email}")
    public List<EmployeeResponse> searchEmail(@PathVariable("email") String email) {

        return employeeService.searchEmail(email);
    }

    /**
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/employee/{page}/{limit}")
    public EmployeeFageResponse ShowPaging(@Min(1) @PathVariable("page") int page,
                                           @PathVariable(name = "limit") int limit) {
        EmployeeFageResponse result = new EmployeeFageResponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListresult(employeeService.findAllPaging(pageable));
        result.setTotalpage((int) Math.ceil((double) (employeeService.totalItem()) / limit));
        return result;
    }

    /**
     * @param employee
     * @return
     */
    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employee) {
        return employeeService.create(employee);
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @PutMapping("/employee/{id}")
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequest model,
                                           @PathVariable("id") long id) {
        model.setId(id);
        return employeeService.create(model);
    }

    /**
     * @param id
     */
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.delete(id);
    }

}
