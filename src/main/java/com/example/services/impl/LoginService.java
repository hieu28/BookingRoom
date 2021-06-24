package com.example.services.impl;

import com.example.exceptions.PasswordNotFound;
import com.example.exceptions.UsernameNotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.security.krb5.KrbCryptoException;

import java.util.Optional;

@Service
public class LoginService {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final RedisTemplate<Object, Object> template;

    @Autowired
    public LoginService(EmployeeService employeeService, EmployeeRepository employeeRepository, RedisTemplate<Object, Object> template) {
        this.template = template;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public EmployeeEntity checkUser(String email, String password) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmail(email);
        if (!employeeOptional.isPresent()) {
            throw new UsernameNotFound();
        }
        if (!password.equals(employeeOptional.get().getPassword())) {
            throw new PasswordNotFound();
        }
        return employeeOptional.get();
    }

    public void LogoutUser(String jwt){
        template.delete(jwt);
    }

}
