package com.crud.diver.mapper;

import com.crud.diver.currency.client.CurrencyExchangeClient;
import com.crud.diver.domain.DivingBase;
import com.crud.diver.domain.DivingBaseDto;
import com.crud.diver.domain.User;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.UserDbService;
import com.crud.diver.weather.client.WeatherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DivingBaseMapperTests {

    @InjectMocks
    private DivingBaseMapper divingBaseMapper;
    @Mock
    private UserDbService userDbService;
    @Mock
    private CurrencyExchangeClient currencyExchangeClient;
    @Mock
    private WeatherClient weatherClient;

    private User createUserData() {
        return User.builder()
                .id(11L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .divingBases(new ArrayList<>())
                .build();
    }
    private DivingBase createDivingBaseData() {
        return DivingBase.builder()
                .id(1L)
                .name("Sun")
                .localization("Split")
                .currencyName("EUR")
                .currencyRate(5.1)
                .temperature(29.0)
                .user(createUserData())
                .build();
    }
    private DivingBaseDto createDivingBaseDtoData() {
        return DivingBaseDto.builder()
                .id(1L)
                .name("Sun")
                .localization("Split")
                .currencyName("EUR")
                .currencyRate(4.4)
                .temperature(29.0)
                .userId(11L)
                .build();
    }

    @Test
    void shouldReturnDivingBaseWhenMapDivingBaseDtoToDivingBase() throws UserNotFoundException {
        // Given
        DivingBaseDto divingBaseDto = createDivingBaseDtoData();
        User user = createUserData();
        when(currencyExchangeClient.getCurrencyExchangeRate(divingBaseDto.getCurrencyName())).thenReturn(4.4);
        when(weatherClient.getTemperature(divingBaseDto.getLocalization())).thenReturn(29.0);
        when(userDbService.getUserById(divingBaseDto.getUserId())).thenReturn(user);
        // When
        DivingBase divingBaseReceived = divingBaseMapper.mapToDivingBase(divingBaseDto);
        // Then
        assertEquals(1L, divingBaseReceived.getId());
        assertEquals("Sun", divingBaseReceived.getName());
        assertEquals("Split", divingBaseReceived.getLocalization());
        assertEquals("EUR", divingBaseReceived.getCurrencyName());
        assertEquals(4.4, divingBaseReceived.getCurrencyRate());
        assertEquals(29.0, divingBaseReceived.getTemperature());
        assertEquals(11L, divingBaseReceived.getUser().getId());
    }

    @Test
    void shouldReturnDivingBaseDtoWhenMapDivingBaseToDivingBaseDto() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        when(currencyExchangeClient.getCurrencyExchangeRate(divingBase.getCurrencyName())).thenReturn(4.4);
        when(weatherClient.getTemperature(divingBase.getLocalization())).thenReturn(29.0);
        // When
        DivingBaseDto divingBaseDtoReceived = divingBaseMapper.mapToDivingBaseDto(divingBase);
        // Then
        assertEquals(1L, divingBaseDtoReceived.getId());
        assertEquals("Sun", divingBaseDtoReceived.getName());
        assertEquals("Split", divingBaseDtoReceived.getLocalization());
        assertEquals("EUR", divingBaseDtoReceived.getCurrencyName());
        assertEquals(4.4, divingBaseDtoReceived.getCurrencyRate());
        assertEquals(29.0, divingBaseDtoReceived.getTemperature());
        assertEquals(11L, divingBaseDtoReceived.getUserId());
    }

    @Test
    void shouldReturnDivingBaseDtoListWhenMapDivingBaseListToDivingBaseDtoList() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        when(currencyExchangeClient.getCurrencyExchangeRate(divingBase.getCurrencyName())).thenReturn(4.4);
        when(weatherClient.getTemperature(divingBase.getLocalization())).thenReturn(29.0);
        // When
        List<DivingBaseDto> divingBaseDtoListReceived = divingBaseMapper.mapToDivingBaseDtoList(List.of(divingBase));
        // Then
        assertEquals(1, divingBaseDtoListReceived.size());
        assertEquals(1L, divingBaseDtoListReceived.get(0).getId());
        assertEquals("Sun", divingBaseDtoListReceived.get(0).getName());
        assertEquals("Split", divingBaseDtoListReceived.get(0).getLocalization());
        assertEquals("EUR", divingBaseDtoListReceived.get(0).getCurrencyName());
        assertEquals(4.4, divingBaseDtoListReceived.get(0).getCurrencyRate());
        assertEquals(29.0, divingBaseDtoListReceived.get(0).getTemperature());
        assertEquals(11L, divingBaseDtoListReceived.get(0).getUserId());
    }
}
