package com.forex.exchange.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyExchange {

        @Id
        private long id;
        @Column(name = "currency_from")
        private String from;
        @Column(name = "currency_to")
        private String to;
        private double conversionMultiple;


}
