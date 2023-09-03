package com.cucumber.test.advices;

import com.cucumber.test.enums.ResultStatus;
import com.cucumber.test.result.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is a global exception handling class in Spring Boot, responsible for handling exceptions that occur within the application. 
 * Typically, it is achieved using @RestControllerAdvice and @ExceptionHandler annotations. 
 * */
@RestControllerAdvice
public class GlobalExceptionHandler { 

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex) {
        Result<?> body = Result.failure(ResultStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;        
        return new ResponseEntity<>(body, headers, status);
    }    
}