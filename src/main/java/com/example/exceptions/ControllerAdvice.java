package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = UserPassnotFound.class)
    public ResponseEntity<Object> exception(UserPassnotFound exception) {
        return new ResponseEntity<>("Username not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordNotFoundException.class)
    public ResponseEntity<Object> exception(PasswordNotFoundException exception) {
        return new ResponseEntity<>("Password not found", HttpStatus.BAD_REQUEST);
    }
}
