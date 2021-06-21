package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.JwtRequest;
import com.example.models.responses.JwtResponse;
import com.example.services.loginservice.LoginService;
import com.example.utils.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;
    private final JwtProvider jwtProvider;

    public LoginController(LoginService loginService, JwtProvider jwtProvider) {
        this.loginService = loginService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        EmployeeEntity employee = loginService.checkUser(username, password);
        String jwt = "";
        if (employee != null) {
            jwt = jwtProvider.generateToken(username);

        }
        return new ResponseEntity<>(new JwtResponse(jwt, username), HttpStatus.OK);
    }
}


