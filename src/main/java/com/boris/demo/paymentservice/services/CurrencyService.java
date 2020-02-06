package com.boris.demo.paymentservice.services;


import java.util.List;

public interface CurrencyService {
    List<String> getCurrencyCodes();

    List<String> getSupportedCurrencyCodes();

}
