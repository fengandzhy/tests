package com.cucumber.test.enums;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UnitAndTeensEnumTest {

    @Test
    public void testGetWord(){
        String word = UnitAndTeensEnum.getWord(3);
        assertEquals("THREE", word);

        word = UnitAndTeensEnum.getWord(4);
        assertEquals("FOUR", word);

        word = UnitAndTeensEnum.getWord(19);
        assertEquals("NINETEEN", word);
    }
}
