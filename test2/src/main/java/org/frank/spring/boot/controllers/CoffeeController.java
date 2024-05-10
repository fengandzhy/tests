package org.frank.spring.boot.controllers;

import org.frank.spring.boot.models.CoffeeResponse;
import org.frank.spring.boot.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/brew-coffee")
    public ResponseEntity<CoffeeResponse> brewCoffee() {
        CoffeeResponse coffeeResponse = coffeeService.brewCoffee();
        return ResponseEntity.ok(coffeeResponse);
    }
}
