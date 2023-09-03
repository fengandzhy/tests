package com.cucumber.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;



/**
 * 1. This application is going to transfer number from 0 to 999999999999.99
 * 
 * 2. I am not a native English speaker, so in some case I felt confusing for example, 
 *  In American English, $1001 is said as 'One thousand and one US dollars,' 
 *  but in British English, people prefer to say 'One thousand one US dollars.' 
 *  This made me a bit confused, and in fact, both ways are correct. It took me quite a while to understand this rule, 
 *  so in my program, I consistently convert $1001 to 'One thousand and one US dollars.'
 *  
 *  
 * 
 * 
 * 
 * */
public class TempTest {
    
    @Test
    public void testNumber1(){
        DecimalFormat decimalFormat = new DecimalFormat(",###.00");
        String formatted = decimalFormat.format(new BigDecimal("1111"));
        String[] array = formatted.split(",");
        System.out.println(array[0]);
    }

    @Test
    public void testNumber2(){
        BigDecimal bigDecimal = new BigDecimal("000");
        System.out.println(bigDecimal.intValue() == 0);
    }

    @Test
    public void testNumber3(){
        StringBuffer stringBuffer = new StringBuffer("");
        System.out.println(stringBuffer.toString().equals(""));
    }
}
