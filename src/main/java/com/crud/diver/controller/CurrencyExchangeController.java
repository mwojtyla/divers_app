package com.crud.diver.controller;

import com.crud.diver.currency.client.CurrencyExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diver/currency")
@RequiredArgsConstructor
public class CurrencyExchangeController {
    private final CurrencyExchangeClient currencyExchangeClient;

    @GetMapping("exchange/{currencyFrom}")
    public ResponseEntity<Double> getCurrencyExchangeRate(@PathVariable String currencyFrom) {
        return ResponseEntity.ok(currencyExchangeClient.getCurrencyExchangeRate(currencyFrom));
    }
}
