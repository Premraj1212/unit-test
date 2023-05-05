package com.forex.exchange.repository;


import com.forex.exchange.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    CurrencyExchange findByFromAndTo(String from, String to);
}
