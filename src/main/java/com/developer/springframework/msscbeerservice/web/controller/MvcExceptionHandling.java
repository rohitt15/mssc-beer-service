package com.developer.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/*
created by Rohit on 29-10-2021
 */
@ControllerAdvice
public class MvcExceptionHandling {
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        List<String> errorList =new ArrayList<>(ex.getConstraintViolations().size());
        ex.getConstraintViolations().forEach(error->errorList.add(error.toString()));
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST); 
    }

}
