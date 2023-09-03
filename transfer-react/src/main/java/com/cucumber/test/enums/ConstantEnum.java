package com.cucumber.test.enums;

public enum ConstantEnum {
    HUNDRED("HUNDRED"),
    HUNDREDS("HUNDREDS"),
    THOUSAND("THOUSAND"),
    THOUSANDS("THOUSANDS"),
    MILLION("MILLION"),
    MILLIONS("MILLIONS"),
    BILLION("BILLION"),
    BILLIONS("BILLIONS"),
    DOLLARS ("DOLLARS"),
    DOLLAR ("DOLLAR"),
    CENT ("CENT"),
    SPACE(" "),
    MINUS("-"),
    AND("AND"),
    CENTS("CENTS");
    private final String word;
    ConstantEnum(String word){        
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
