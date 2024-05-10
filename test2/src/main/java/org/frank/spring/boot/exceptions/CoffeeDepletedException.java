package org.frank.spring.boot.exceptions;

public class CoffeeDepletedException  extends RuntimeException {
    public CoffeeDepletedException(String message) {
        super(message);
    }
}
