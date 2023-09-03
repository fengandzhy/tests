package com.cucumber.test.enums;


/**
 * This Enum is for the number less than 20.
 * */
public enum UnitAndTeensEnum {
    ZERO(0,""),
    ONE(1,"ONE"),
    TWO(2,"TWO"),
    THREE(3,"THREE"),
    FOUR(4,"FOUR"),
    FIVE(5,"FIVE"),
    SIX(6,"SIX"),
    SEVEN(7,"SEVEN"),
    EIGHT(8,"EIGHT"),
    NINE(9,"NINE"),
    TEN(10,"TEN"),
    ELEVEN(11,"ELEVEN"),
    TWELVE(12,"TWELVE"),
    THIRTEEN(13,"THIRTEEN"),
    FOURTEEN(14,"FOURTEEN"),
    FIFTEEN(15,"FIFTEEN"),
    SIXTEEN(16,"SIXTEEN"),
    SEVENTEEN(17,"SEVENTEEN"),
    EIGHTEEN(18,"EIGHTEEN"),
    NINETEEN(19,"NINETEEN");    
    
    private int number;
    private String word;
    UnitAndTeensEnum(int number, String word){
        this.number = number;
        this.word = word;
    }

    public static String getWord(int number) {
        for (UnitAndTeensEnum e : UnitAndTeensEnum.values()) {
            if (e.getNumber() == number) {
                return e.word;
            }
        }
        return null;
    } 
    
    private int getNumber(){
        return this.number;
    }
}
