package com.forex.exchange.service;


import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchange getCurrencyExchangesRates(String fromCurrency, String toCurrency){
       return currencyExchangeRepository.findByFromAndTo(fromCurrency,toCurrency);
    }
}
