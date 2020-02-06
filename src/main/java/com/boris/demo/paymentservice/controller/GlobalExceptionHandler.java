package com.boris.demo.paymentservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception ex){
        log.info("Exception Occured:: URL="+request.getRequestURL() + ", exception ex ", ex );
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST );
    }
}
