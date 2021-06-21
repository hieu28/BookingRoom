package com.example.services.loginservice;

import ch.qos.logback.core.pattern.color.BoldCyanCompositeConverter;
import com.example.exceptions.PasswordNotFoundException;
import com.example.exceptions.UserPassnotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.services.impl.EmployeeService;
import com.example.utils.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final EmployeeService employeeService;
    private final JwtProvider jwtProvider;

    public LoginService(EmployeeService employeeService, JwtProvider jwtProvider) {
        this.employeeService = employeeService;
        this.jwtProvider = jwtProvider;
    }


    public JwtProvider checkUser(String email, String password) throws Exception {
        EmployeeEntity employee = employeeService.findEmployee(email);
        if(!email.equals(employee.getEmail()))throw new UserPassnotFound(); {
            System.out.println("Username not found!");
        }
        if(!password.equals(employee.getPassword())) throw new PasswordNotFoundException();{
            System.out.println("Password not found!");
        }
        return jwtProvider;

    }




}
