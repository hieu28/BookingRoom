package com.example.services.impl;

import com.example.exceptions.PasswordNotFound;
import com.example.exceptions.UsernameNotFound;
import com.example.models.entities.EmployeeEntity;
import com.example.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final EmployeeRepository employeeRepository;
    private final RedisTemplate<Object, Object> template;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(EmployeeRepository employeeRepository, RedisTemplate<Object, Object> template, PasswordEncoder passwordEncoder) {
        this.template = template;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmployeeEntity checkUser(String email, String password) {

        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmail(email);

        if (!employeeOptional.isPresent()) {
            throw new UsernameNotFound();
        }

        if (!passwordEncoder.matches(password, employeeOptional.get().getPassword().trim())) {
            throw new PasswordNotFound();
        }

        return employeeOptional.get();
    }

    public void LogoutUser(String jwt){
        template.delete(jwt);
    }



}
