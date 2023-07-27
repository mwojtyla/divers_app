package com.crud.diver.facade;

import com.crud.diver.domain.*;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.DivingBaseMapper;
import com.crud.diver.service.DivingBaseDbService;
import com.crud.diver.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DivingBaseServiceFacadeTests {

    @InjectMocks
    private DivingBaseServiceFacade divingBaseServiceFacade;

    @Mock
    private DivingBaseMapper divingBaseMapper;

    @Mock
    private DivingBaseDbService divingBaseDbService;

    @Mock
    private UserDbService userDbService;

    private DivingBase createDivingBaseData() {
        return DivingBase.builder()
                .id(1L)
                .name("Sun")
                .localization("Split")
                .currencyName("EUR")
                .currencyRate(5.1)
                .temperature(10.1)
                .user(new User())
                .build();
    }

    private DivingBaseDto createDivingBaseDtoData() {
        return DivingBaseDto.builder()
                .id(1L)
                .name("Sun")
                .localization("Split")
                .currencyName("EUR")
                .currencyRate(5.1)
                .temperature(10.1)
                .userId(11L)
                .build();
    }

    @Test
    void shouldGetDivingBasesByUserId() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        DivingBaseDto divingBaseDto = createDivingBaseDtoData();

        when(divingBaseDbService.getDivingBasesByUserId(11L)).thenReturn(List.of(divingBase));
        when(divingBaseMapper.mapToDivingBaseDtoList(List.of(divingBase))).thenReturn(List.of(divingBaseDto));
        // When
        List<DivingBaseDto> divingBaseListReceived = divingBaseServiceFacade.getDivingBasesByUserId(11L);
        // Then
        assertEquals(1, divingBaseListReceived.size());
        assertEquals(1L, divingBaseListReceived.get(0).getId());
        assertEquals("Sun", divingBaseListReceived.get(0).getName());
        assertEquals("Split", divingBaseListReceived.get(0).getLocalization());
        assertEquals("EUR", divingBaseListReceived.get(0).getCurrencyName());
        assertEquals(5.1, divingBaseListReceived.get(0).getCurrencyRate());
        assertEquals(10.1, divingBaseListReceived.get(0).getTemperature());
        assertEquals(11L, divingBaseListReceived.get(0).getUserId());
    }

    @Test
    void shouldCreateDivingBase() throws UserNotFoundException {
        // Given
        DivingBase divingBase = createDivingBaseData();
        DivingBaseDto divingBaseDto = createDivingBaseDtoData();

        when(divingBaseMapper.mapToDivingBase(divingBaseDto)).thenReturn(divingBase);
        when(userDbService.getUserById(11L)).thenReturn(new User());
        when(divingBaseDbService.saveDivingBase(divingBase)).thenReturn(divingBase);

        // When
        divingBaseServiceFacade.createDivingBase(divingBaseDto);
        // Then
        Mockito.verify(divingBaseMapper, times(1)).mapToDivingBase(divingBaseDto);
        Mockito.verify(userDbService, times(1)).getUserById(11L);
        Mockito.verify(divingBaseDbService, times(1)).saveDivingBase(divingBase);
    }

    @Test
    void shouldUpdateDivingBase() throws UserNotFoundException {
        // Given
        DivingBase divingBase = createDivingBaseData();
        DivingBaseDto divingBaseDto = createDivingBaseDtoData();

        when(divingBaseMapper.mapToDivingBase(divingBaseDto)).thenReturn(divingBase);
        when(divingBaseDbService.saveDivingBase(divingBase)).thenReturn(divingBase);
        when(divingBaseMapper.mapToDivingBaseDto(divingBase)).thenReturn(divingBaseDto);
        // When
        divingBaseServiceFacade.updateDivingBase(divingBaseDto);
        // Then
        Mockito.verify(divingBaseMapper, times(1)).mapToDivingBase(divingBaseDto);
        Mockito.verify(divingBaseDbService, times(1)).saveDivingBase(divingBase);
        Mockito.verify(divingBaseMapper, times(1)).mapToDivingBaseDto(divingBase);
    }

    @Test
    void shouldDeleteDivingBase() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        // When
        divingBaseServiceFacade.deleteDivingBase(divingBase.getId());
        // Then
        Mockito.verify(divingBaseDbService, times(1)).deleteDivingBase(divingBase.getId());
    }
}
