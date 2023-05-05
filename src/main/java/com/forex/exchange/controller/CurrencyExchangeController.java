package com.forex.exchange.controller;



import com.forex.exchange.exceptions.CurrencyNotFoundException;
import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {

    @Autowired
    CurrencyExchangeService currencyExchangeService;


    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}",produces = "application/json")
    public ResponseEntity<CurrencyExchange> exchangeCurrency(@PathVariable String from, @PathVariable String to){
        CurrencyExchange byFromAndTo = currencyExchangeService.getCurrencyExchangesRates(from, to);
        if (byFromAndTo==null) throw new CurrencyNotFoundException("currency pair was not found");
        else{
            return ResponseEntity.ok(byFromAndTo);
        }
    }
}
