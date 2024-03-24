package com.cucumber.test.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This is a class used in Spring Boot to handle cross-origin access (CORS).
 * */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowCredentials(true) 
//                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Origin", "Accept", "Content-Type", "Authorization") 
                .allowCredentials(true);
    }
}

/**
 * /*
 * We are building a word processor and we would like to implement a "word-wrap" functionality.
 *
 * Given a list of words followed by a maximum number of characters in a line, return a collection of strings where each string element represents a line that contains as many words as possible, with the words in each line being concatenated with a single '-' (representing a space, but easier to see for testing). The length of each string must not exceed the maximum character length per line.
 *
 * Your function should take in the maximum characters per line and return a data structure representing all lines in the indicated max length.
 *
 * Examples:
 *
 * words1 = [ "The","day", "began", "as", "still", "as", "the",
 *           "night", "abruptly", "lighted", "with", "brilliant",
 *           "flame" ]
 *
 * wrapLines(words1, 13) "wrap words1 to line length 13" =>
 *
 *   [ "The-day-began",
 *     "as-still-as",
 *     "the-night",
 *     "abruptly",
 *     "lighted-with",
 *     "brilliant",
 *     "flame" ]
 *
 * wrapLines(words1, 12) "wrap words1 to line length 12" =>
 *
 *   [ "The-day",
 *     "began-as",
 *     "still-as-the",
 *     "night",
 *     "abruptly",
 *     "lighted-with",
 *     "brilliant",
 *     "flame" ]    
 *
 *
 * wrapLines(words1, 20) "wrap words1 to line length 20" =>
 *
 *   [ "The-day-began-as",
 *     "still-as-the-night",
 *     "abruptly-lighted",
 *     "with-brilliant-flame" ]
 *
 * words2 = [ "Hello" ]
 *
 * wrapLines(words2, 5) "wrap words2 to line length 5" =>
 *
 *   [ "Hello" ]
 *
 *
 * wrapLines(words2, 30) "wrap words2 to line length 30" =>
 *
 *   [ "Hello" ]  
 *
 * words3 = [ "Hello", "Hello" ]
 *
 * wrapLines(words3, 5) "wrap words3 to line length 5" =>
 *
 *   [ "Hello",
 *   "Hello" ]
 *
 * words4 = ["Well", "Hello", "world" ]
 *
 * wrapLines(words4, 5) "wrap words4 to line length 5" =>
 *
 *   [ "Well",
 *   "Hello",
 *   "world" ]
 *
 * words5 = ["Hello", "HelloWorld", "Hello", "Hello"]
 *
 * wrapLines(words5, 20) "wrap words 5 to line length 20 =>
 *
 *   [ "Hello-HelloWorld",
 *     "Hello-Hello" ]
 *
 *
 * words6 = [ "a", "b", "c", "d" ]
 * wrapLines(words6, 20) "wrap words 6 to line length 20 =>
 *
 *   [ "a-b-c-d" ]
 *
 * wrapLines(words6, 4) "wrap words 6 to line length 4 =>
 *
 *   [ "a-b",
 *     "c-d" ]
 *
 * wrapLines(words6, 1) "wrap words 6 to line length 1 =>
 *
 *   [ "a",
 *     "b",
 *     "c",
 *     "d" ]
 *
 * All Test Cases:
 *           words,  max line length
 * wrapLines(words1, 13)
 * wrapLines(words1, 12)
 * wrapLines(words1, 20)
 * wrapLines(words2, 5)
 * wrapLines(words2, 30)
 * wrapLines(words3, 5)
 * wrapLines(words4, 5)
 * wrapLines(words5, 20)
 * wrapLines(words6, 20)
 * wrapLines(words6, 4)
 * wrapLines(words6, 1)
 *
 * n = number of words OR total characters
 * */
// *
//         *import java.io.*;
//         *import java.util.*;
//         *import javafx.util.Pair;
//         *
//         *
//
//public class Solution {
// *
//
//    public static void main(String[] argv) {
// *String[] words1 = {"The", "day", "began", "as", "still", "as", "the", "night", "abruptly", "lighted", "with", "brilliant", "flame"};
// *String[] words2 = {"Hello"};
// *String[] words3 = {"Hello", "Hello"};
// *String[] words4 = {"Well", "Hello", "world"};
// *String[] words5 = {"Hello", "HelloWorld", "Hello", "Hello"};
// *String[] words6 = {"a", "b", "c", "d"};
// *     
// *Solution s = new Solution();
// *List<String> words = s.testWordJoint(words1, 20);
// * //     wrapLines(words1, 13)
// * // wrapLines(words1, 12)
// * // wrapLines(words1, 20)
// * // wrapLines(words2, 5)
// * // wrapLines(words2, 30)
// * // wrapLines(words3, 5)
// * // wrapLines(words4, 5)
// * // wrapLines(words5, 20)
// * // wrapLines(words6, 20)
// * // wrapLines(words6, 4)
// * // wrapLines(words6, 1)
// *for (String word : words) {
// *System.out.println(word);
// *}  
// *}
// *
//         *
//
//    public List<String> testWordJoint(String[] words, int target) {
// *int length = words.length;
// *int slowPointer = 0;
// *int fastPointer = 1;
// *List<String> wordsList = new ArrayList<>();
// *while (true) {      
// *StringBuilder sb = new StringBuilder();
// *String slowPointerWord = words[slowPointer];
// *sb.append(slowPointerWord);
// *if (fastPointer > length - 1) {
// *wordsList.add(sb.toString());        
// *break;
// *}
// *while (true) {
// *if (fastPointer > length - 1) {
// *wordsList.add(sb.toString());        
// *break;
// *} 
// *         // System.out.println(fastPointer); 
// *         // System.out.println(sb.toString());      
// *String fastPointerWord = words[fastPointer];        
// *if (sb.length() + fastPointerWord.length() <= target - 1) {
// *sb.append("-");
// *sb.append(fastPointerWord);          
// *fastPointer = fastPointer + 1;
// *} else {
// *wordsList.add(sb.toString());
// *slowPointer = fastPointer;
// *fastPointer = fastPointer + 1; 
// *break;
// *}
// *}                  
// *}
// *return wordsList;   
// *}
// *
//}
// * */
