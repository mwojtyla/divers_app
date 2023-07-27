package com.crud.diver.service;

import com.crud.diver.domain.DivingBase;
import com.crud.diver.domain.User;
import com.crud.diver.repository.DivingBaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class DivingBaseServiceTests {

    @InjectMocks
    private DivingBaseDbService divingBaseDbService;
    @Mock
    private DivingBaseRepository divingBaseRepository;

    private User createUserData() {
        return User.builder()
                .id(1L)
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
                .currencyRate(4.4)
                .temperature(29.0)
                .user(createUserData())
                .build();
    }

    @Test
    void shouldGetDivingBasesByUserId() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        User user = createUserData();
        user.getDivingBases().add(divingBase);
        when(divingBaseRepository.findByUserId(user.getId())).thenReturn(List.of(divingBase));
        // When
        List<DivingBase> divingBaseListReceived = divingBaseDbService.getDivingBasesByUserId(user.getId());
        // Then
        assertEquals(1, divingBaseListReceived.size());
        assertEquals(1L, divingBaseListReceived.get(0).getId());
        assertEquals("Sun", divingBaseListReceived.get(0).getName());
        assertEquals("Split", divingBaseListReceived.get(0).getLocalization());
        assertEquals("EUR", divingBaseListReceived.get(0).getCurrencyName());
        assertEquals(4.4, divingBaseListReceived.get(0).getCurrencyRate());
        assertEquals(29.0, divingBaseListReceived.get(0).getTemperature());
        assertEquals(1L, divingBaseListReceived.get(0).getUser().getId());
    }

    @Test
    void shouldSaveDivingBase() {
        // Given
        DivingBase divingBase = createDivingBaseData();
        User user = createUserData();
        user.getDivingBases().add(divingBase);
        when(divingBaseRepository.save(divingBase)).thenReturn(divingBase);
        // When
        DivingBase divingBaseReceived = divingBaseDbService.saveDivingBase(divingBase);
        // Then
        assertEquals(1L, divingBaseReceived.getId());
        assertEquals("Sun", divingBaseReceived.getName());
        assertEquals("Split", divingBaseReceived.getLocalization());
        assertEquals("EUR", divingBaseReceived.getCurrencyName());
        assertEquals(4.4, divingBaseReceived.getCurrencyRate());
        assertEquals(29.0, divingBaseReceived.getTemperature());
        assertEquals(1L, divingBaseReceived.getUser().getId());
    }

    @Test
    void shouldDeleteDivingBase() {
        // Given
        Long divingBaseId = 1L;
        // When
        divingBaseDbService.deleteDivingBase(divingBaseId);
        // Then
        Mockito.verify(divingBaseRepository, times(1)).deleteById(divingBaseId);
    }
}
