package com.crud.diver.controller;

import com.crud.diver.currency.client.CurrencyExchangeClient;
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
@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeClient currencyExchangeClient;

    @Test
    void shouldGetCurrencyExchangeRate() throws Exception {
        // Given
        String currencyFrom = "USD";
        when(currencyExchangeClient.getCurrencyExchangeRate(currencyFrom)).thenReturn(5.0);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/currency/exchange/USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(5.0)));
    }
}
