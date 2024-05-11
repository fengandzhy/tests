package org.frank.spring.boot.service.impl;

import org.frank.spring.boot.exceptions.AprilFoolsException;
import org.frank.spring.boot.exceptions.CoffeeDepletedException;
import org.frank.spring.boot.generators.CoffeeResponseGenerator;
import org.frank.spring.boot.models.CoffeeResponse;
import org.frank.spring.boot.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    @Autowired
    private CoffeeResponseGenerator coffeeResponseGenerator;
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private AtomicInteger requestCount = new AtomicInteger(0);
    
    @Override
    public CoffeeResponse brewCoffee() {
        ZonedDateTime now = ZonedDateTime.now();
        if (now.getMonthValue() == 4 && now.getDayOfMonth() == 1) {
            throw new AprilFoolsException("I’m a teapot");
        }

        int currentCount = requestCount.incrementAndGet();
        if (currentCount % 5 == 0) {
            throw new CoffeeDepletedException("Service Unavailable");
        }

        String currentTime = now.format(DATE_TIME_FORMATTER);        
        return coffeeResponseGenerator.brewCoffee("Your piping hot coffee is ready", currentTime);
    }
}
