//package com.example.controllers;
//
//import com.example.models.requests.JwtRequest;
//import com.example.models.responses.JwtResponse;
//import com.example.services.loginservice.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LoginController {
//    @Autowired
//    LoginService loginService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthentication(@RequestBody JwtRequest jwtRequest) throws Exception {
//        String username = "jd@mggg.com";
//        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqZEBtZ2dnLmNvbSIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0._J2H9SEiwPS6OPxaOcyiFLVfrM9tKvqKulTUhMGQvf8";
//        loginService.checkUser(jwtRequest.getUsername(), jwtRequest.getPassword());
//
//
//        return new ResponseEntity<>(new JwtResponse(jwt, username), HttpStatus.OK);
//    }
//}
