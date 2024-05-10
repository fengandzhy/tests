package org.frank.spring.boot.generators.impl;

import org.frank.spring.boot.generators.CoffeeResponseGenerator;
import org.frank.spring.boot.models.CoffeeResponse;
import org.springframework.stereotype.Component;

@Component
public class CoffeeResponseGeneratorImpl implements CoffeeResponseGenerator {

    @Override
    public CoffeeResponse brewCoffee(String message, String time) {
        return new CoffeeResponse(message,time);
    }
}
