package com.cucumber.test.enums;

public enum DivisorEnums {

    HUNDRED(100),    
    THOUSAND(1000),    
    MILLION(1000000),    
    BILLION(1000000000);    
    
    private final int divisor;

    DivisorEnums(int divisor){
        this.divisor = divisor;
    }

    public int getDivisor() {
        return divisor;
    }
}
