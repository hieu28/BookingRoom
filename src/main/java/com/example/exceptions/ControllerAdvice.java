package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = UsernameNotFound.class)
    public ResponseEntity<Object> exception(UsernameNotFound exception) {
        return new ResponseEntity<>("Username not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordNotFound.class)
    public ResponseEntity<Object> exception(PasswordNotFound exception) {
        return new ResponseEntity<>("Password not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtNotFound.class)
    public ResponseEntity<Object> exception(JwtNotFound exception) {
        return new ResponseEntity<>(" JWT not found ,Unauthorize", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtSingnature.class)
    public ResponseEntity<Object> exception(JwtSingnature exception) {
        return new ResponseEntity<>("Invalid JWT signature, Unauthorize", HttpStatus.UNAUTHORIZED);
    }
}
