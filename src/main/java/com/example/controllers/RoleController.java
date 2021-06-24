//package com.example.controllers;
//
//
//import com.example.models.requests.EmployeeRequest;
//import com.example.models.requests.RoleRequest;
//import com.example.models.responses.EmployeeResponse;
//import com.example.models.responses.RoleResponse;
//import com.example.services.IRoleService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping
//public class RoleController {
//
//    @Autowired
//    IRoleService roleService;
//
//
//    @GetMapping("/role")
//    public void findAllRole(@RequestBody RoleRequest roles) {
//        roleService.save(roles);
//    }
//
//    @PostMapping("/role")
//    public RoleResponse createRole(@RequestBody RoleRequest role) {
//        return roleService.save(role);
//    }
//
//    @GetMapping("/role/{id}")
//    public RoleResponse getById(@PathVariable("id") long id) {
//        return roleService.findById(id);
//    }
//
//    @PutMapping("/role/{id}")
//    public RoleResponse updateEmployee(@RequestBody RoleRequest m, @PathVariable("id") long id) {
//        m.setId(id);
//        return roleService.save(m);
//    }
//
//    @DeleteMapping("/role/{id}")
//    public void deleteEmployee(@PathVariable("id") long id) {
//        roleService.delete(id);
//    }
//
//}
