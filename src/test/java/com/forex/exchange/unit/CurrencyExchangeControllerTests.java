package com.forex.exchange.unit;


import com.forex.exchange.controller.CurrencyExchangeController;
import com.forex.exchange.model.CurrencyExchange;
import com.forex.exchange.service.CurrencyExchangeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(CurrencyExchangeController.class)
@ExtendWith(SpringExtension.class)
public class CurrencyExchangeControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CurrencyExchangeService currencyExchangeService;


    @Test
    @SneakyThrows
    public void should_return_a_currency(){

        CurrencyExchange currencyExchange = CurrencyExchange.builder()
                .id(100).from("USD").to("INR").conversionMultiple(82.5).build();
        Mockito.when(currencyExchangeService.getCurrencyExchangesRates(anyString(),anyString())).thenReturn(currencyExchange);

        mockMvc.perform(MockMvcRequestBuilders.get("/currency-exchange/from/USD/to/INR"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.from").value("USD"))
                .andDo(MockMvcResultHandlers.print());

    }
}
