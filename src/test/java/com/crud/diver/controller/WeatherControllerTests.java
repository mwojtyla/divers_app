package com.crud.diver.controller;

import com.crud.diver.weather.client.WeatherClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
public class WeatherControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient weatherClient;

    @Test
    void shouldGetTemperature() throws Exception {
        // Given
        String localization = "Poznan";
        when(weatherClient.getTemperature(localization)).thenReturn(20.0);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/weather/temperature/Poznan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(20.0)));
    }
}
