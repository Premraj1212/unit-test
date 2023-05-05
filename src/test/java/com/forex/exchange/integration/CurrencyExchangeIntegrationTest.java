package com.forex.exchange.integration;

import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.repository.CurrencyExchangeRepository;
import com.forex.exchange.service.CurrencyExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyExchangeIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @Sql(statements = "insert into currency_exchange (id,CONVERSION_MULTIPLE  , CURRENCY_FROM  , CURRENCY_TO  ) values (1007,0.68,'SGD','EUR');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from currency_exchange where id=1007;",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_exchange_rate_ccy_pair(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CurrencyExchange> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/currency-exchange/from/{from}/to/{to}",
                HttpMethod.GET,httpEntity,CurrencyExchange.class,"SGD","EUR");

        //Assertions
        Assertions.assertEquals(200,responseEntity.getStatusCode().value());
        Assertions.assertEquals(0.68,responseEntity.getBody().getConversionMultiple());
    }
}
