package com.crud.diver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherConfig {

    @Value("${weather.api.endpoint.prod}")
    private String weatherApiEndpoint;

    @Value("${weather.app.key}")
    private String weatherAppKey;

    @Value("${weather.app.host}")
    private String weatherAppHost;
}
