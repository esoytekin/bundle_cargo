package com.bundle.cargo.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler({FeignException.NotFound.class, FeignException.InternalServerError.class})
    public ResponseEntity <String> handleException(FeignException e) {
        return ResponseEntity.status (e.status ()).body (HttpStatus.valueOf (e.status ()).getReasonPhrase ());
    }
}
