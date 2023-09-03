package com.cucumber.test.services.impl;

import com.cucumber.test.enums.DivisorEnums;
import com.cucumber.test.enums.TensEnum;
import com.cucumber.test.enums.UnitAndTeensEnum;
import com.cucumber.test.services.NumberConvertToWordsService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.cucumber.test.enums.ConstantEnum.*;

@Service
public class NumberConvertToWordsServiceImpl implements NumberConvertToWordsService {
    
    @NotNull
    @Override
    public String convertNumberToWords(@NotNull String number) {        
        String formattedAmount = this.formatAmount(new BigDecimal(number));
        String[] parts = formattedAmount.split("\\.");
        String centsPart = processCentsPart(parts[1]);
        String dollarsPart = processDollarsPart(parts[0]);
        StringBuilder stringBuilder = new StringBuilder();
        if(centsPart.isEmpty()){
            return dollarsPart;
        }
        return stringBuilder.append(dollarsPart).append(SPACE.getWord()).append(AND.getWord()).append(SPACE.getWord()).append(centsPart).toString();
    }

    @NotNull
    private String formatAmount(@NotNull BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat("###.00");
        return decimalFormat.format(amount);
    }
    
    @NotNull
    private String processUnitsAndTes(@NotNull BigDecimal bigDecimal){        
        StringBuilder stringBuilder = new StringBuilder();
        int number = bigDecimal.intValue();
        if (number == 0) {
            return stringBuilder.toString();
        }
        if(number < 20 && number > 0){
            return UnitAndTeensEnum.getWord(number);
        }
        stringBuilder.append(TensEnum.getWord(number / 10));
        int remainder = number % 10;
        if (remainder != 0) {
            stringBuilder.append(MINUS.getWord()).append(UnitAndTeensEnum.getWord(remainder));
        }
        return stringBuilder.toString();       
    }

    @NotNull
    private String processHundreds(@NotNull BigDecimal bigDecimal){
        StringBuilder stringBuilder = new StringBuilder();
        int number = bigDecimal.intValue();
        int hundreds = number / 100;
        int reminder = number % 100;
        if(hundreds == 0){
            return this.processUnitsAndTes(BigDecimal.valueOf(reminder));
        }
        stringBuilder.append(UnitAndTeensEnum.getWord(hundreds));
        stringBuilder.append(SPACE.getWord());
        stringBuilder.append(HUNDRED.getWord());   

        String tenAndUnitStr = this.processUnitsAndTes(BigDecimal.valueOf(reminder));
        if(tenAndUnitStr.isEmpty()){
            return stringBuilder.toString();
        }        
        stringBuilder.append(SPACE.getWord());
        stringBuilder.append(AND.getWord());
        stringBuilder.append(SPACE.getWord());
        stringBuilder.append(tenAndUnitStr);
        return stringBuilder.toString();
    }
    
    @NotNull
    private String processNumber(@NotNull BigDecimal bigDecimal, DivisorEnums enums){
        StringBuilder stringBuilder = new StringBuilder();        
        int number = bigDecimal.divide(BigDecimal.valueOf(enums.getDivisor()), RoundingMode.DOWN).intValue();
        if(number == 0){
            return stringBuilder.toString();
        }        
        String numberStr = processHundreds(BigDecimal.valueOf(number));
        String unit = "";
        switch (enums){
            case BILLION:
                unit = BILLION.getWord();
                break;
            case MILLION:
                unit = MILLION.getWord();
                break;
            case THOUSAND:
                unit = THOUSAND.getWord();
                break;
            default: break;
        }        
        stringBuilder.append(numberStr);
        stringBuilder.append(SPACE.getWord());
        stringBuilder.append(unit);
        return stringBuilder.toString();
    }
    
    @NotNull
    private String processCentsPart(@NotNull String centsPart){
        StringBuilder stringBuilder = new StringBuilder();
        BigDecimal cents = new BigDecimal(centsPart);
        if(cents.intValue() == 0){
            return stringBuilder.toString();
        }
        String centsWithOutUnit = processUnitsAndTes(new BigDecimal(centsPart));
        String centsUnit = addUnit(centsWithOutUnit, false);
        if(centsWithOutUnit.isEmpty()){
            return stringBuilder.toString();
        }
        return stringBuilder.append(centsWithOutUnit).append(SPACE.getWord()).append(centsUnit).toString();
    }

    @NotNull
    private String processDollarsPart(@NotNull String dollarsPart){
        StringBuilder stringBuilder = new StringBuilder();
        BigDecimal dollars = new BigDecimal(dollarsPart);
        
        if (dollars.intValue() == 0){
            return stringBuilder.toString();
        }
        String billionPart = processNumber(dollars, DivisorEnums.BILLION);
        String millionPart = processNumber(dollars.remainder(BigDecimal.valueOf(DivisorEnums.BILLION.getDivisor())), DivisorEnums.MILLION);
        String thousandPart = processNumber(dollars.remainder(BigDecimal.valueOf(DivisorEnums.MILLION.getDivisor())), DivisorEnums.THOUSAND);
        String hundredPart = processHundreds(dollars.remainder(BigDecimal.valueOf(DivisorEnums.THOUSAND.getDivisor())));
        if(!billionPart.isEmpty()){
            stringBuilder.append(billionPart);
        }
        if(!millionPart.isEmpty()){
            addAndJoint(stringBuilder.toString(),stringBuilder);
            stringBuilder.append(millionPart);
        }
        if(!thousandPart.isEmpty()){
            addAndJoint(stringBuilder.toString(),stringBuilder);
            stringBuilder.append(thousandPart);
        }
        if(!hundredPart.isEmpty()){
            addAndJoint(stringBuilder.toString(),stringBuilder);
            stringBuilder.append(hundredPart);
        }
        String dollarWithOutUnit = stringBuilder.toString();        
        String dollarUnit = addUnit(dollarWithOutUnit, true);        
        return stringBuilder.append(SPACE.getWord()).append(dollarUnit).toString();  
    }

    @NotNull
    private String addUnit(@NotNull String str, boolean isDollar){
        StringBuilder stringBuilder = new StringBuilder();        
        if(str.equals("ONE")){
            if(isDollar){
                stringBuilder.append(DOLLAR.getWord());
            }else{
                stringBuilder.append(CENT.getWord());
            }            
            return stringBuilder.toString();
        }       
        if(isDollar){
            stringBuilder.append(DOLLARS.getWord());
        }else{
            stringBuilder.append(CENTS.getWord());
        }        
        return stringBuilder.toString();       
    }
    
    private void addAndJoint(@NotNull String dollarStr, @NotNull StringBuilder stringBuilder){
        if(!dollarStr.isEmpty()){
            stringBuilder.append(SPACE.getWord());            
        }
    }
}
