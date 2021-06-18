package com.example.services.loginservice;

import com.example.exceptions.PasswordNotFoundException;
import com.example.exceptions.UserPassnotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.services.impl.EmployeeService;
import com.example.utils.JwtProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final EmployeeService employeeService;
    private JwtProvider jwtProvider;

    public LoginService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public JwtProvider checkUser(String email, String password) throws Exception {
        EmployeeEntity employee = employeeService.findEmployee(email);
        if(!email.equals(employee.getEmail()))throw new UserPassnotFound(); {
            System.out.println("cac");
        }
        if(!password.equals(employee.getPassword())) throw new PasswordNotFoundException();{
            System.out.println("cac");
        }
        return jwtProvider;

    }


}
