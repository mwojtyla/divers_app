package com.crud.diver.controller;

import com.crud.diver.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diver/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherClient weatherClient;

    @GetMapping("/temperature/{localization}")
    public ResponseEntity<Double> getWeather(@PathVariable String localization) {
        return ResponseEntity.ok(weatherClient.getTemperature(localization));
    }
}
