package com.example.services.loginservice;

import com.example.exceptions.PasswordNotFoundException;
import com.example.exceptions.UsernameNotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.repositories.EmployeeRepository;
import com.example.services.impl.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public LoginService(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public EmployeeEntity checkUser(String email, String password) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmail(email);
        if (!employeeOptional.isPresent()) {
            throw new UsernameNotFound();
        }
        if(!password.equals(employeeOptional.get().getPassword())){
            throw new PasswordNotFoundException();
        }
        return employeeOptional.get();


        //System.out.println(employee.getEmail());
//        if (!email.equals(employee.getEmail())) throw new UsernameNotFound();
//        {
//            System.out.println("Username not found!");
//        }
//        if (!password.equals(employee.getPassword())) throw new PasswordNotFoundException();
//        {
//            System.out.println("Password not found!");
//        }
        //return employee;
    }

}
