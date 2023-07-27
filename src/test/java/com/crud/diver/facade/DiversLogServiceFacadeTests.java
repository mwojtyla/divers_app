package com.crud.diver.facade;

import com.crud.diver.domain.*;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.DiversLogMapper;
import com.crud.diver.service.DiversLogDbService;
import com.crud.diver.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiversLogServiceFacadeTests {
    @InjectMocks
    private DiversLogServiceFacade diversLogServiceFacade;
    @Mock
    private DiversLogMapper diversLogMapper;
    @Mock
    private DiversLogDbService diversLogDbService;
    @Mock
    private UserDbService userDbService;

    private DiversLog createDiversLogData() {
        return DiversLog.builder()
                .id(1L)
                .dateOfDiving(LocalDate.of(2023, 5, 20))
                .localization("Split")
                .visibility(5)
                .airTemperature(25.0)
                .surfaceTemperature(25.0)
                .bottomTemperature(20.0)
                .weight(5.0)
                .depth(15.0)
                .timeOfDiving(100)
                .timeIn(LocalTime.of(12, 30, 30))
                .timeOut(LocalTime.of(13, 40, 30))
                .conditions("Fresh, shore")
                .airUsed(100)
                .user(new User())
                .build();
    }

    private DiversLogDto createDiversLogDtoData() {
        return new DiversLogDto.DiversLogDtoBuilder()
                .id(1L)
                .dateOfDiving(LocalDate.of(2023, 5, 20))
                .localization("Split")
                .visibility(5)
                .airTemperature(25.0)
                .surfaceTemperature(25.0)
                .bottomTemperature(20.0)
                .weight(5.0)
                .depth(15.0)
                .timeOfDiving(100)
                .timeIn(LocalTime.of(12, 30, 30))
                .timeOut(LocalTime.of(13, 40, 30))
                .conditions("Fresh, shore")
                .airUsed(100)
                .userId(11L)
                .build();
    }

    @Test
    void shouldGetDiversLogsByUserId() {
        // Given
        DiversLog diversLog = createDiversLogData();
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(diversLogDbService.getDiversLogsByUserId(diversLogDto.getUserId())).thenReturn(List.of(diversLog));
        when(diversLogMapper.mapToDiversLogDtoList(List.of(diversLog))).thenReturn(List.of(diversLogDto));
        // When
        List<DiversLogDto> diversLogListReceived = diversLogServiceFacade.getDiversLogsByUserId(diversLogDto.getUserId());
        // Then
        assertEquals(1, diversLogListReceived.size());
        assertEquals(1L, diversLogListReceived.get(0).getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogListReceived.get(0).getDateOfDiving());
        assertEquals("Split", diversLogListReceived.get(0).getLocalization());
        assertEquals(5, diversLogListReceived.get(0).getVisibility());
        assertEquals(25, diversLogListReceived.get(0).getAirTemperature());
        assertEquals(25, diversLogListReceived.get(0).getSurfaceTemperature());
        assertEquals(20, diversLogListReceived.get(0).getBottomTemperature());
        assertEquals(5, diversLogListReceived.get(0).getWeight());
        assertEquals(15, diversLogListReceived.get(0).getDepth());
        assertEquals(100, diversLogListReceived.get(0).getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogListReceived.get(0).getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogListReceived.get(0).getTimeOut());
        assertEquals("Fresh, shore", diversLogListReceived.get(0).getConditions());
        assertEquals(100, diversLogListReceived.get(0).getAirUsed());
        assertEquals(11L, diversLogListReceived.get(0).getUserId());
    }

    @Test
    void shouldCreateDiversLog() throws UserNotFoundException {
        // Given
        DiversLog diversLog = createDiversLogData();
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(diversLogMapper.mapToDiversLog(diversLogDto)).thenReturn(diversLog);
        when(userDbService.getUserById(11L)).thenReturn(new User());
        when(diversLogDbService.saveDiversLog(diversLog)).thenReturn(diversLog);
        // When
        diversLogServiceFacade.createDiversLog(diversLogDto);
        // Then
        Mockito.verify(diversLogMapper, times(1)).mapToDiversLog(diversLogDto);
        Mockito.verify(userDbService, times(1)).getUserById(11L);
        Mockito.verify(diversLogDbService, times(1)).saveDiversLog(diversLog);
    }


    @Test
    void shouldUpdateDiversLog() throws UserNotFoundException {
        // Given
        DiversLog diversLog = createDiversLogData();
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(diversLogMapper.mapToDiversLog(diversLogDto)).thenReturn(diversLog);
        when(diversLogDbService.saveDiversLog(diversLog)).thenReturn(diversLog);
        when(diversLogMapper.mapToDiversLogDto(diversLog)).thenReturn(diversLogDto);
        // When
        diversLogServiceFacade.updateDiversLog(diversLogDto);
        // Then
        Mockito.verify(diversLogMapper, times(1)).mapToDiversLog(diversLogDto);
        Mockito.verify(diversLogDbService, times(1)).saveDiversLog(diversLog);
        Mockito.verify(diversLogMapper, times(1)).mapToDiversLogDto(diversLog);
    }

    @Test
    void shouldDeleteDiversLog() {
        // Given
        DiversLog diversLog = createDiversLogData();
        // When
        diversLogServiceFacade.deleteDiversLog(diversLog.getId());
        // Then
        Mockito.verify(diversLogDbService, times(1)).deleteDiversLog(diversLog.getId());
    }

    @Test
    void shouldGetDiversLogByLocalizationAndUserId() {
        // Given
        DiversLog diversLog = createDiversLogData();
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(diversLogDbService.getDiversLogsByUserId(diversLogDto.getUserId())).thenReturn(List.of(diversLog));
        when(diversLogMapper.mapToDiversLogDtoList(List.of(diversLog))).thenReturn(List.of(diversLogDto));
        // When
        List<DiversLogDto> diversLogListReceived = diversLogServiceFacade.getDiversLogByLocalizationAndUserId(diversLogDto.getLocalization(), diversLogDto.getUserId());
        // Then
        assertEquals(1, diversLogListReceived.size());
        assertEquals(1L, diversLogListReceived.get(0).getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogListReceived.get(0).getDateOfDiving());
        assertEquals("Split", diversLogListReceived.get(0).getLocalization());
        assertEquals(5, diversLogListReceived.get(0).getVisibility());
        assertEquals(25, diversLogListReceived.get(0).getAirTemperature());
        assertEquals(25, diversLogListReceived.get(0).getSurfaceTemperature());
        assertEquals(20, diversLogListReceived.get(0).getBottomTemperature());
        assertEquals(5, diversLogListReceived.get(0).getWeight());
        assertEquals(15, diversLogListReceived.get(0).getDepth());
        assertEquals(100, diversLogListReceived.get(0).getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogListReceived.get(0).getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogListReceived.get(0).getTimeOut());
        assertEquals("Fresh, shore", diversLogListReceived.get(0).getConditions());
        assertEquals(100, diversLogListReceived.get(0).getAirUsed());
        assertEquals(11L, diversLogListReceived.get(0).getUserId());
    }

    @Test
    void shouldGetDiversLogByDepthAndUserId() {
        // Given
        DiversLog diversLog = createDiversLogData();
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(diversLogDbService.getDiversLogByDepthAndUserId(diversLogDto.getDepth(), diversLogDto.getUserId())).thenReturn(List.of(diversLog));
        when(diversLogMapper.mapToDiversLogDtoList(List.of(diversLog))).thenReturn(List.of(diversLogDto));
        // When
        List<DiversLogDto> diversLogListReceived = diversLogServiceFacade.getDiversLogByDepthAndUserId(diversLogDto.getDepth(), diversLogDto.getUserId());
        // Then
        assertEquals(1, diversLogListReceived.size());
        assertEquals(1L, diversLogListReceived.get(0).getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogListReceived.get(0).getDateOfDiving());
        assertEquals("Split", diversLogListReceived.get(0).getLocalization());
        assertEquals(5, diversLogListReceived.get(0).getVisibility());
        assertEquals(25, diversLogListReceived.get(0).getAirTemperature());
        assertEquals(25, diversLogListReceived.get(0).getSurfaceTemperature());
        assertEquals(20, diversLogListReceived.get(0).getBottomTemperature());
        assertEquals(5, diversLogListReceived.get(0).getWeight());
        assertEquals(15, diversLogListReceived.get(0).getDepth());
        assertEquals(100, diversLogListReceived.get(0).getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogListReceived.get(0).getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogListReceived.get(0).getTimeOut());
        assertEquals("Fresh, shore", diversLogListReceived.get(0).getConditions());
        assertEquals(100, diversLogListReceived.get(0).getAirUsed());
        assertEquals(11L, diversLogListReceived.get(0).getUserId());
    }
}
