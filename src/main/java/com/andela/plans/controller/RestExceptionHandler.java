package com.andela.plans.controller;

import com.andela.plans.exceptions.PlanLimitedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PlanLimitedException.class)
    public ResponseEntity<String> handlePlanLimitedException(){
        return new ResponseEntity<>("you need to add start and end date in limited plans", HttpStatus.BAD_REQUEST);
    }
}
