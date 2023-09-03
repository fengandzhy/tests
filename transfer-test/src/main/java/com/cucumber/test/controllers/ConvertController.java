package com.cucumber.test.controllers;

import com.cucumber.test.services.NumberConvertToWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

/**
 * This controller is responsible for catching the parameters from front-end pages and redirect to the right page
 * */
@Controller
public class ConvertController {
    
    private NumberConvertToWordsService numberConvertToWordsService;
    
    /**
     * catch the username and money
     * */
    @RequestMapping("/test/convert")
    public String convert(@NotNull String username, @NotNull String money, @NotNull Model model){
        String moneyWords = numberConvertToWordsService.convertNumberToWords(money);
        model.addAttribute("username",username);
        model.addAttribute("moneyWords",moneyWords);
        return "result";
    }

    @Autowired
    public void setNumberConvertToWordsService(NumberConvertToWordsService numberConvertToWordsService) {
        this.numberConvertToWordsService = numberConvertToWordsService;
    }
}
