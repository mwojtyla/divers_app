package com.crud.diver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CurrencyExchangeConfig {

    @Value("${currency.exchange.api.endpoint.prod}")
    private String currencyExchangeApiEndpoint;


    @Value("${currency.exchange.app.key}")
    private String currencyExchangeAppKey;

    @Value("${currency.exchange.app.host}")
    private String currencyExchangeAppHost;
}
