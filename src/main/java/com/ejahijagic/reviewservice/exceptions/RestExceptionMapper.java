package com.ejahijagic.reviewservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionMapper {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handler(NoSuchElementException e) {
        return ResponseEntity.status(NOT_FOUND).build();
    }
}
