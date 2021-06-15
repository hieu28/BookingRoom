package com.example.controllers;

import com.example.models.requests.JwtRequest;
import com.example.models.responses.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @PostMapping("/authen")
    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest){
        String username = "jd@mggg.com";
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqZEBtZ2dnLmNvbSIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0._J2H9SEiwPS6OPxaOcyiFLVfrM9tKvqKulTUhMGQvf8";

        return new ResponseEntity<>(new JwtResponse(jwt,username),HttpStatus.OK);
    }
}
