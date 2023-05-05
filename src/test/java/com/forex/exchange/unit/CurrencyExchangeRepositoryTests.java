package com.forex.exchange.unit;


import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.repository.CurrencyExchangeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CurrencyExchangeRepositoryTests {

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void should_get_currency_pair(){

        CurrencyExchange byFromAndTo = currencyExchangeRepository.findByFromAndTo("USD", "INR");

        Assertions.assertEquals("USD",byFromAndTo.getFrom());
        Assertions.assertEquals("INR",byFromAndTo.getTo());
        Assertions.assertEquals(82.0,byFromAndTo.getConversionMultiple());

    }
}
