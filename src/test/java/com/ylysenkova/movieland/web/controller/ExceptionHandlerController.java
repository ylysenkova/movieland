package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.web.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
