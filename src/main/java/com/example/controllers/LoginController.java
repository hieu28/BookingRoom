package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.JwtRequest;
import com.example.models.responses.JwtResponse;
import com.example.services.impl.LoginService;
import com.example.utils.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest) {

        EmployeeEntity employee = loginService.checkUser(jwtRequest.getUsername(), jwtRequest.getPassword());
        String token = "";
        if (employee != null) {
            token = jwtProvider.generateToken(jwtRequest.getUsername());
        }
        return new ResponseEntity<>(new JwtResponse(token, jwtRequest.getUsername()), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> Logout(@RequestParam(name ="jwt") String jwt){
        loginService.LogoutUser(jwt);
        return new ResponseEntity<>("log out thanh cong", HttpStatus.OK);
    }
}


