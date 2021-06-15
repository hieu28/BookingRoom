package com.example.controller;

import com.example.model.request.JwtRequest;
import com.example.model.response.JwtResponse;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {


    @PostMapping("/authen")
    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest){
        String username = "jd@mggg.com";
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqZEBtZ2dnLmNvbSIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0._J2H9SEiwPS6OPxaOcyiFLVfrM9tKvqKulTUhMGQvf8";

        return new ResponseEntity<>(new JwtResponse(jwt,username),HttpStatus.OK);
    }
}
