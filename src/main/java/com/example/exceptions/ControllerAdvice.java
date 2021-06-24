package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req){
        return new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }

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
        return new ResponseEntity<>(" Authorization Required ", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JwtSingnature.class)
    public ResponseEntity<Object> exception(JwtSingnature exception) {
        return new ResponseEntity<>("Invalid JWT signature, Unauthorize", HttpStatus.UNAUTHORIZED);
    }
}
