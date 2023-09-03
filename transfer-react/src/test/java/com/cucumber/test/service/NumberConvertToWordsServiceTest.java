package com.cucumber.test.service;

import com.cucumber.test.enums.DivisorEnums;
import com.cucumber.test.services.NumberConvertToWordsService;
import com.cucumber.test.services.impl.NumberConvertToWordsServiceImpl;
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
    public void testProcessUnitsAndTes(){        
        
        String tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processUnitsAndTes", new BigDecimal("00"));
        assertEquals("", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processUnitsAndTes", new BigDecimal("01"));
        assertEquals("ONE", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processUnitsAndTes", new BigDecimal("30"));
        assertEquals("THIRTY", tensDigitWords);

        tensDigitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processUnitsAndTes", new BigDecimal("31"));
        assertEquals("THIRTY-ONE", tensDigitWords);
    }

    @Test
    public void testProcessHundreds(){        

        String HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("000"));
        assertEquals("", HundredsWords);

        HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("023"));
        assertEquals("TWENTY-THREE", HundredsWords);

        HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("100"));
        assertEquals("ONE HUNDRED", HundredsWords);

        HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("200"));
        assertEquals("TWO HUNDRED", HundredsWords);

        HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("119"));
        assertEquals("ONE HUNDRED AND NINETEEN", HundredsWords);

        HundredsWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processHundreds", new BigDecimal("232"));
        assertEquals("TWO HUNDRED AND THIRTY-TWO", HundredsWords);
    }
    
    @Test
    public void testProcessCentsPart() {
              
        String centsPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "45");
        assertEquals("FORTY-FIVE CENTS", centsPart);
        centsPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "00");
        assertEquals("", centsPart);

        centsPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "01");
        assertEquals("ONE CENT", centsPart);

        centsPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "02");
        assertEquals("TWO CENTS", centsPart);

        centsPart = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processCentsPart", "19");
        assertEquals("NINETEEN CENTS", centsPart);
    }

    @Test
    public void testProcessNumber() {
        
        String numberStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processNumber", new BigDecimal(1122334455), DivisorEnums.BILLION);
        assertEquals("ONE BILLION", numberStr);

        numberStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processNumber", new BigDecimal(122334455), DivisorEnums.MILLION);
        assertEquals("ONE HUNDRED AND TWENTY-TWO MILLION", numberStr);
    }
    
    @Test
    public void testProcessDollarsPart() {
        
        String dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "0");
        assertEquals("", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1");
        assertEquals("ONE DOLLAR", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "2");
        assertEquals("TWO DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100");
        assertEquals("ONE HUNDRED DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "111");
        assertEquals("ONE HUNDRED AND ELEVEN DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "231");
        assertEquals("TWO HUNDRED AND THIRTY-ONE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1133");
        assertEquals("ONE THOUSAND ONE HUNDRED AND THIRTY-THREE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "2211");
        assertEquals("TWO THOUSAND TWO HUNDRED AND ELEVEN DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "200001");
        assertEquals("TWO HUNDRED THOUSAND ONE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "333023");
        assertEquals("THREE HUNDRED AND THIRTY-THREE THOUSAND TWENTY-THREE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100000001");
        assertEquals("ONE HUNDRED MILLION ONE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "100038001");
        assertEquals("ONE HUNDRED MILLION THIRTY-EIGHT THOUSAND ONE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "999999999999");
        assertEquals("NINE HUNDRED AND NINETY-NINE BILLION NINE HUNDRED AND NINETY-NINE MILLION NINE HUNDRED AND NINETY-NINE THOUSAND NINE HUNDRED AND NINETY-NINE DOLLARS", dollarStr);

        dollarStr = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "processDollarsPart", "1000000000");
        assertEquals("ONE BILLION DOLLARS", dollarStr);        
    }

    @Test
    public void testAddUnit(){       

        String unitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "addUnit", "ONE", true);
        assertEquals("DOLLAR", unitWords);

        unitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "addUnit", "TWO HUNDRED AND THIRTY-TWO", true);
        assertEquals("DOLLARS", unitWords);

        unitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "addUnit", "ONE", false);
        assertEquals("CENT", unitWords);

        unitWords = ReflectionTestUtils.invokeMethod(numberConvertToWordsService, "addUnit", "TWENTY-THREE", false);
        assertEquals("CENTS", unitWords);
    }
    
    @Test
    public void testConvertNumberToWords(){
        String moneyWords = numberConvertToWordsService.convertNumberToWords("123.45");
        assertEquals("ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS", moneyWords);
    }
}
