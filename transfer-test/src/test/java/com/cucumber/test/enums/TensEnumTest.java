package com.cucumber.test.enums;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TensEnumTest {
    
    @Test
    public void testGetWord(){
        String word = TensEnum.getWord(3);
        assertEquals("THIRTY", word);

        word = TensEnum.getWord(9);
        assertEquals("NINETY", word);
    }
}
