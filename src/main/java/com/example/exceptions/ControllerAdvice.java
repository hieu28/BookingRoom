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

    @ExceptionHandler(value = PasswordNotFoundException.class)
    public ResponseEntity<Object> exception(PasswordNotFoundException exception) {
        return new ResponseEntity<>("Password not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtNotFound.class)
    public ResponseEntity<Object> exception(JwtNotFound exception) {
        return new ResponseEntity<>(" JWT not found ,Unauthorize", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtSingnatureException.class)
    public ResponseEntity<Object> exception(JwtSingnatureException exception) {
        return new ResponseEntity<>("Invalid JWT signature", HttpStatus.UNAUTHORIZED);
    }
}
