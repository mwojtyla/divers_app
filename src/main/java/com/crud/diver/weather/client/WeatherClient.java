package com.crud.diver.weather.client;

import com.crud.diver.config.WeatherConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    public double getTemperature(String localization) {
        URI url = UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherApiEndpoint())
                .queryParam("q", localization)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-RapidAPI-Key", weatherConfig.getWeatherAppKey());
        headers.set("X-RapidAPI-Host", weatherConfig.getWeatherAppHost());

        HttpEntity request = new HttpEntity(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    String.class);

            double temperature = 0.0;

            for (int i = 0; i < response.getBody().length(); i++) {
                if ("t".equals(String.valueOf(response.getBody().charAt(i)))
                        && "e".equals(String.valueOf(response.getBody().charAt(i + 1)))
                        && "m".equals(String.valueOf(response.getBody().charAt(i + 2)))
                        && "p".equals(String.valueOf(response.getBody().charAt(i + 3)))
                        && "_".equals(String.valueOf(response.getBody().charAt(i + 4)))
                        && "c".equals(String.valueOf(response.getBody().charAt(i + 5)))) {
                    temperature = Double.parseDouble(response.getBody().substring(i + 8, i + 12));
                }
            }
            return Optional.ofNullable(temperature).orElse(0.0);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return 0.0;
        }
    }
}
