package com.cucumber.test.services.impl;

import com.cucumber.test.enums.TensEnum;
import com.cucumber.test.enums.UnitAndTeensEnum;
import com.cucumber.test.services.NumberConvertToWordsService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.cucumber.test.enums.UnitEnum.*;

@Service
public class NumberConvertToWordsServiceImpl implements NumberConvertToWordsService {

//    private static final String[] UNITS = {BILLIONS.getWord(), MILLIONS.getWord(), THOUSANDS.getWord(), ""};
    private static final String[] UNIT = {BILLION.getWord(), MILLION.getWord(), THOUSAND.getWord(), ""};

    @Override
    @NotNull
    public String convertNumberToWords(@NotNull String number) {
        BigDecimal amount = new BigDecimal(number);
        String formattedAmount = this.formatAmount(amount);
        String[] parts = formattedAmount.split("\\.");
        String centPart = processCentsPart(parts[1]);
        String dollarPart = processDollarsPart(parts[0]);
        StringBuilder stringBuffer = new StringBuilder();
        if(centPart.isEmpty()){
            return dollarPart;
        }
        return stringBuffer.append(dollarPart).append(SPACE.getWord()).append(AND.getWord()).append(SPACE.getWord()).append(centPart).toString();
    }

    /**
     * format the amount for example if input is 22333222.78, format it to 22,333,222.78
     * Author: Frank
     */
    @NotNull
    private String formatAmount(@NotNull BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat(",###.00");
        return decimalFormat.format(amount);
    }

    /**
     * convert a number to word, for example 01 to ONE CENT, 23 to TWENTY-THREE CENTS
     * Author: Frank
     */
    @NotNull
    private String processCentsPart(@NotNull String centsPart) {
        StringBuilder stringBuffer = new StringBuilder();
        BigDecimal bigDecimal = new BigDecimal(centsPart);
        if (bigDecimal.intValue() == 1) {
            stringBuffer.append(UnitAndTeensEnum.getWord(bigDecimal.intValue()));
            stringBuffer.append(SPACE.getWord());
            stringBuffer.append(CENT.getWord());
            return stringBuffer.toString();
        }
        String tensDigit = this.processTensDigit(bigDecimal);
        if (!tensDigit.isEmpty()) {
            stringBuffer.append(tensDigit);
            stringBuffer.append(SPACE.getWord());
            stringBuffer.append(CENTS.getWord());
        }
        return stringBuffer.toString();
    }

    /**
     * convert a number to word, for example 1 to ONE DOLLAR, 23 to TWENTY-THREE DOLLARS
     * Author: Frank
     */
    @NotNull
    private String processDollarsPart(@NotNull String dollarPart) {
        StringBuilder stringBuffer = new StringBuilder();        
        if (dollarPart.equals("1")) { 
            stringBuffer.append(UnitAndTeensEnum.getWord(1));
            stringBuffer.append(SPACE.getWord());
            stringBuffer.append(DOLLAR.getWord());
            return stringBuffer.toString();
        }
        String[] numberArray = dollarPart.split(",");        
        int numberArrayLength = numberArray.length;
        int unitArrayLength = UNIT.length;
        List<String> words = new ArrayList<>();
        for (int i = 0; i < numberArrayLength; i++) {
            String tempWord = processHundredsDigit(new BigDecimal(numberArray[i]), unitArrayLength - numberArrayLength + i);
            words.add(tempWord);
        }
        for (int i = 0; i < words.size(); i++) {
            if (i > 0 && !words.get(i).isEmpty()) {
                stringBuffer.append(SPACE.getWord());
//                stringBuffer.append(AND.getWord());
//                stringBuffer.append(SPACE.getWord());
            }
            stringBuffer.append(words.get(i));
        }
        stringBuffer.append(SPACE.getWord());
        stringBuffer.append(DOLLARS.getWord());
        return stringBuffer.toString().trim();
    }


    /**
     * convert a number to word, for example 20 to TWENTY, 23 to TWENTY-THREE
     * Author: Frank
     */
    @NotNull
    private String processTensDigit(@NotNull BigDecimal bigDecimal) {
        StringBuilder stringBuffer = new StringBuilder();
        if (bigDecimal.intValue() < 20 && bigDecimal.intValue() > 0) {
            return UnitAndTeensEnum.getWord(bigDecimal.intValue());
        }
        int number = bigDecimal.intValue();
        if (number == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append(TensEnum.getWord(number / 10));
        int remainder = number % 10;
        if (remainder != 0) {
            stringBuffer.append(MINUS.getWord()).append(UnitAndTeensEnum.getWord(remainder));
        }
        return stringBuffer.toString();
    }

    /**
     * convert a number to words according to the right unit 
     * for example 10000 will be converted to TEN THOUSANDS, 100 will be converted to ONE HUNDRED
     * Author: Frank
     * Params:  
     *      bigDecimal: the number
     *      index: the position of the right unit.
     */
    @NotNull
    private String processHundredsDigit(@NotNull BigDecimal bigDecimal, int index) {
        StringBuilder stringBuffer = new StringBuilder();
        if (bigDecimal.intValue() == 0) {
            return stringBuffer.toString();
        }
        int number = bigDecimal.intValue();
        int hundreds = number / 100;
        if (hundreds > 0) { 
            stringBuffer.append(UnitAndTeensEnum.getWord(hundreds));
            stringBuffer.append(SPACE.getWord());
            stringBuffer.append(HUNDRED);
        }               
//        if (hundreds == 1) {
//            stringBuffer.append(SPACE.getWord());
//            stringBuffer.append(HUNDRED);
//        }
//        if (hundreds > 1) {
//            stringBuffer.append(SPACE.getWord());
//            stringBuffer.append(HUNDREDS);
//        }
        int remainder = number % 100;
        String tens = processTensDigit(new BigDecimal(remainder));
        if (!tens.isEmpty()) {
            if (hundreds > 0) {
                stringBuffer.append(SPACE.getWord());
                stringBuffer.append(AND.getWord());
                stringBuffer.append(SPACE.getWord());
            }           
            stringBuffer.append(tens);
        }
//        stringBuffer.append(SPACE.getWord());
//        stringBuffer.append(UNIT[index]);
//        if (hundreds == 0 && remainder == 1 && !UNITS[index].isEmpty()) {
//            stringBuffer.append(SPACE.getWord());
//            stringBuffer.append(UNIT[index]);
//            return stringBuffer.toString();
//        }
        if (!UNIT[index].isEmpty()) {
            stringBuffer.append(SPACE.getWord());
            stringBuffer.append(UNIT[index]);
        }
        return stringBuffer.toString();
    }
}
