package org.frank.spring.boot.handlers;

import org.frank.spring.boot.exceptions.AprilFoolsException;
import org.frank.spring.boot.exceptions.CoffeeDepletedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoffeeDepletedException.class)
    public ResponseEntity<?> handleCoffeeDepletedException(CoffeeDepletedException ex) {
        return ResponseEntity.status(503).body("");
    }

    @ExceptionHandler(AprilFoolsException.class)
    public ResponseEntity<?> handleAprilFoolsException(AprilFoolsException ex) {
        return ResponseEntity.status(418).body("");
    }
}
