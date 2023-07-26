package com.crud.diver.currency.client;

import com.crud.diver.config.CurrencyExchangeConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeClient {

    private final RestTemplate restTemplate;
    private final CurrencyExchangeConfig currencyExchangeConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeClient.class);

    public double getCurrencyExchangeRate(String currencyFrom) {
        URI url = UriComponentsBuilder.fromHttpUrl(currencyExchangeConfig.getCurrencyExchangeApiEndpoint())
                .queryParam("from", currencyFrom)
                .queryParam("to", "PLN")
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Key", currencyExchangeConfig.getCurrencyExchangeAppKey());
        headers.set("X-RapidAPI-Host", currencyExchangeConfig.getCurrencyExchangeAppHost());

        HttpEntity request = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class);
            return Optional.ofNullable(Double.parseDouble(response.getBody())).orElse(0.00);
        } catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return 0.00;
        }
    }
}
