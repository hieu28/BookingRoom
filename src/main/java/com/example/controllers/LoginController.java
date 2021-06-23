package com.example.controllers;

import com.example.models.entities.EmployeeEntity;
import com.example.models.requests.JwtRequest;
import com.example.models.responses.JwtResponse;
import com.example.services.impl.LoginService;
import com.example.utils.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping({"/hello"})
    public String index(){
        return "Hello";
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest) throws Exception {
//        String username = jwtRequest.getUsername();
//        String password = jwtRequest.getPassword();
        EmployeeEntity employee = loginService.checkUser(jwtRequest.getUsername(), jwtRequest.getPassword());
        String token = "";
        if (employee != null) {
            token = jwtProvider.generateToken(jwtRequest.getUsername());

        }
        return new ResponseEntity<>(new JwtResponse(token, jwtRequest.getUsername()), HttpStatus.OK);
    }
}


