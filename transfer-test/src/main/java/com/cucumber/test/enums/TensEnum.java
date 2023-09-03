package com.cucumber.test.enums;

public enum TensEnum {
    TWENTY(2,"TWENTY"),
    THIRTY(3,"THIRTY"),
    FORTY(4,"FORTY"),
    FIFTY(5,"FIFTY"),
    SIXTY(6,"SIXTY"),
    SEVENTY(7,"SEVENTY"),
    EIGHTY(8,"EIGHTY"),
    NINETY(9,"NINETY");
    private int number;
    private String word;
    TensEnum(int number, String word){
        this.number = number;
        this.word = word;
    }

    public static String getWord(int number) {
        for (TensEnum e : TensEnum.values()) {
            if (e.getNumber() == number) {
                return e.word;
            }
        }
        return null;
    }

    public int getNumber(){
        return this.number;
    }
}
