package com.forex.exchange.unit;


import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.repository.CurrencyExchangeRepository;
import com.forex.exchange.service.CurrencyExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeServiceTests {

    @Mock
    CurrencyExchangeRepository currencyExchangeRepository;

    @InjectMocks
    CurrencyExchangeService currencyExchangeService;

    @Test
    public void getCurrencyPair(){

        CurrencyExchange currencyExchange = CurrencyExchange.builder().id(1).from("USD").to("INR").conversionMultiple(82).build();

        Mockito.when(currencyExchangeRepository.findByFromAndTo(anyString(),anyString())).thenReturn(currencyExchange);

        CurrencyExchange currencyExchangesRates = currencyExchangeService.getCurrencyExchangesRates("USD", "INR");

        Assertions.assertEquals("USD",currencyExchangesRates.getFrom());
        Assertions.assertEquals("INR",currencyExchangesRates.getTo());
        Assertions.assertEquals(82,currencyExchangesRates.getConversionMultiple());
    }
}
