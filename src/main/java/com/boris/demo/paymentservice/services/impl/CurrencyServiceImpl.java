package com.boris.demo.paymentservice.services.impl;

import com.boris.demo.paymentservice.services.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public List<String> getCurrencyCodes() {
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
        return currencySet.stream().map( c-> c.getCurrencyCode() ).collect( Collectors.toList() );
    }

    @Override
    public List<String> getSupportedCurrencyCodes() {
        return Arrays.asList( "USD", "EUR", "NIS" );
    }
}
