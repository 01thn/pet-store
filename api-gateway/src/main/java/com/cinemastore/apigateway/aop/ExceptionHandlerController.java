package com.cinemastore.apigateway.aop;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.status()));
    }
}
