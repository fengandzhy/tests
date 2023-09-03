package com.cucumber.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 1. This application is going to transfer number from 0 to 999999999999.99
 *
 * 2.In some case I felt confusing for example, 
 *  In American English, $1001 is said as 'One thousand and one US dollars,' 
 *  but in British English, people prefer to say 'One thousand one US dollars.' 
 *  This made me a bit confused, and in fact, both ways are correct. It took me quite a while to understand this rule, 
 *  so in my program, I consistently convert $1001 to 'One thousand and one US dollars.'
 *   
 * */
@SpringBootApplication
public class TestApp {

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }
}
