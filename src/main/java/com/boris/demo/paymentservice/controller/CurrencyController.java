package com.boris.demo.paymentservice.controller;

import com.boris.demo.paymentservice.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping( path = "/currency/codes")
    public List<String> getCurrencyCodes(){
        return currencyService.getCurrencyCodes();
    }

    @GetMapping( path = "/currency/supported/codes")
    public List<String> getSupportedCurrencyCodes(){
        return currencyService.getSupportedCurrencyCodes();
    }
}
