package com.cucumber.test.services;

import javax.validation.constraints.NotNull;

public interface NumberConvertToWordsService {
    
    @NotNull
    String convertNumberToWords(@NotNull String number);
}
