package com.cucumber.test.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NumberConvertToWordsServiceTest {
    
    @Autowired
    private NumberConvertToWordsService numberConvertToWordsService;
    
    @Test
    public void testFormatAmount(){
        String formattedNumber = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "formatAmount", new BigDecimal("22333222.78"));
        assertEquals("22,333,222.78", formattedNumber);       
    }

    @Test
    public void testProcessCentsPart(){
        String centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "01");
        assertEquals("ONE CENT", centPart);

        centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "20");
        assertEquals("TWENTY CENTS", centPart);

        centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "23");
        assertEquals("TWENTY-THREE CENTS", centPart);

        centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "88");
        assertEquals("EIGHTY-EIGHT CENTS", centPart);

        centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "19");
        assertEquals("NINETEEN CENTS", centPart);

        centPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "00");
        assertEquals("", centPart);
    }

    @Test
    public void testProcessTensDigit(){
        String tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processTensDigit", new BigDecimal("20"));
        assertEquals("TWENTY", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processTensDigit", new BigDecimal("90"));
        assertEquals("NINETY", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processTensDigit", new BigDecimal("23"));
        assertEquals("TWENTY-THREE", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processTensDigit", new BigDecimal("00"));
        assertEquals("", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processTensDigit", new BigDecimal("01"));
        assertEquals("ONE", tensDigitWords);
    }


    @Test
    public void testProcessHundredsDigit(){
        String tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("100"),3);
        assertEquals("ONE HUNDRED", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("200"),3);
        assertEquals("TWO HUNDRED", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("000"),3);
        assertEquals("", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("001"),3);
        assertEquals("ONE", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("020"),3);
        assertEquals("TWENTY", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("222"),3);
        assertEquals("TWO HUNDRED AND TWENTY-TWO", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("435"),1);
        assertEquals("FOUR HUNDRED AND THIRTY-FIVE MILLION", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("001"),2);
        assertEquals("ONE THOUSAND", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("002"),2);
        assertEquals("TWO THOUSAND", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("022"),2);
        assertEquals("TWENTY-TWO THOUSAND", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundredsDigit", new BigDecimal("020"),1);
        assertEquals("TWENTY MILLION", tensDigitWords);
    }

    @Test
    public void testProcessDollarsPart(){
        String dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1");
        assertEquals("ONE DOLLAR", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "2");
        assertEquals("TWO DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "22");
        assertEquals("TWENTY-TWO DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100");
        assertEquals("ONE HUNDRED DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "111");
        assertEquals("ONE HUNDRED AND ELEVEN DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1,111");
        assertEquals("ONE THOUSAND ONE HUNDRED AND ELEVEN DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1,211");
        assertEquals("ONE THOUSAND TWO HUNDRED AND ELEVEN DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1,000,001");
        assertEquals("ONE MILLION ONE DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100,000,001");
        assertEquals("ONE HUNDRED MILLION ONE DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100,038,001");
        assertEquals("ONE HUNDRED MILLION THIRTY-EIGHT THOUSAND ONE DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "999,999,999,999");
        assertEquals("NINE HUNDRED AND NINETY-NINE BILLION NINE HUNDRED AND NINETY-NINE MILLION NINE HUNDRED AND NINETY-NINE THOUSAND NINE HUNDRED AND NINETY-NINE DOLLARS", dollarPart);

        dollarPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1,000,000,000");
        assertEquals("ONE BILLION DOLLARS", dollarPart);
    }

    @Test
    public void testConvertNumberToWords(){
        String words = numberConvertToWordsService.convertNumberToWords("00001.00");
        assertEquals("ONE DOLLAR", words);

        words = numberConvertToWordsService.convertNumberToWords("00001.01");
        assertEquals("ONE DOLLAR AND ONE CENT", words);

        words = numberConvertToWordsService.convertNumberToWords("1.11");
        assertEquals("ONE DOLLAR AND ELEVEN CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("999999999999.99");
        assertEquals("NINE HUNDRED AND NINETY-NINE BILLION NINE HUNDRED AND NINETY-NINE MILLION NINE HUNDRED AND NINETY-NINE THOUSAND NINE HUNDRED AND NINETY-NINE DOLLARS AND NINETY-NINE CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("100000.11");
        assertEquals("ONE HUNDRED THOUSAND DOLLARS AND ELEVEN CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("2113.45");
        assertEquals("TWO THOUSAND ONE HUNDRED AND THIRTEEN DOLLARS AND FORTY-FIVE CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("12113.45");
        assertEquals("TWELVE THOUSAND ONE HUNDRED AND THIRTEEN DOLLARS AND FORTY-FIVE CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("212113.45");
        assertEquals("TWO HUNDRED AND TWELVE THOUSAND ONE HUNDRED AND THIRTEEN DOLLARS AND FORTY-FIVE CENTS", words);

        words = numberConvertToWordsService.convertNumberToWords("21212113.45");
        assertEquals("TWENTY-ONE MILLION TWO HUNDRED AND TWELVE THOUSAND ONE HUNDRED AND THIRTEEN DOLLARS AND FORTY-FIVE CENTS", words);
    }
}
