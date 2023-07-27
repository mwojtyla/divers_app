package com.crud.diver.mapper;

import com.crud.diver.domain.DiversLog;
import com.crud.diver.domain.DiversLogDto;
import com.crud.diver.domain.User;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiversLogMapperTests {
    @InjectMocks
    private DiversLogMapper diversLogMapper;
    @Mock
    private UserDbService userDbService;
    private User createUserData(){
        return User.builder()
                .id(11L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .diversLogs(new ArrayList<>())
                .build();
    }
    private DiversLog createDiversLogData(){
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
                .user(createUserData())
                .build();
    }
    private DiversLogDto createDiversLogDtoData(){
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
    void shouldReturnDiversLogWhenMapDiversLogDtoToDiversLog() throws UserNotFoundException {
        // Given
        User user = createUserData();
        DiversLog diversLog = createDiversLogData();
        user.getDiversLogs().add(diversLog);
        DiversLogDto diversLogDto = createDiversLogDtoData();

        when(userDbService.getUserById(diversLogDto.getUserId())).thenReturn(user);
        // When
        DiversLog diversLogReceived = diversLogMapper.mapToDiversLog(diversLogDto);
        // Then
        assertEquals(1L, diversLogReceived.getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogReceived.getDateOfDiving());
        assertEquals("Split", diversLogReceived.getLocalization());
        assertEquals(5, diversLogReceived.getVisibility());
        assertEquals(25, diversLogReceived.getAirTemperature());
        assertEquals(25, diversLogReceived.getSurfaceTemperature());
        assertEquals(20, diversLogReceived.getBottomTemperature());
        assertEquals(5, diversLogReceived.getWeight());
        assertEquals(15, diversLogReceived.getDepth());
        assertEquals(100, diversLogReceived.getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogReceived.getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogReceived.getTimeOut());
        assertEquals("Fresh, shore", diversLogReceived.getConditions());
        assertEquals(100, diversLogReceived.getAirUsed());
        assertEquals(11L, diversLogReceived.getUser().getId());
    }

    @Test
    void shouldReturnDiversLogDtoWhenMapDiversLogToDiversLogDto() {
        // Given
        User user = createUserData();
        DiversLog diversLog = createDiversLogData();
        user.getDiversLogs().add(diversLog);
        // When
        DiversLogDto diversLogDtoReceived = diversLogMapper.mapToDiversLogDto(diversLog);
        // Then
        assertEquals(1L, diversLogDtoReceived.getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogDtoReceived.getDateOfDiving());
        assertEquals("Split", diversLogDtoReceived.getLocalization());
        assertEquals(5, diversLogDtoReceived.getVisibility());
        assertEquals(25, diversLogDtoReceived.getAirTemperature());
        assertEquals(25, diversLogDtoReceived.getSurfaceTemperature());
        assertEquals(20, diversLogDtoReceived.getBottomTemperature());
        assertEquals(5, diversLogDtoReceived.getWeight());
        assertEquals(15, diversLogDtoReceived.getDepth());
        assertEquals(100, diversLogDtoReceived.getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogDtoReceived.getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogDtoReceived.getTimeOut());
        assertEquals("Fresh, shore", diversLogDtoReceived.getConditions());
        assertEquals(100, diversLogDtoReceived.getAirUsed());
        assertEquals(11L, diversLogDtoReceived.getUserId());
    }

    @Test
    void shouldReturnDiversLogDtoListWhenMapDiversLogListToDiversLogDtoList() {
        // Given
        User user = createUserData();
        DiversLog diversLog = createDiversLogData();
        user.getDiversLogs().add(diversLog);
        // When
        List<DiversLogDto> diversLogDtoListReceived = diversLogMapper.mapToDiversLogDtoList(List.of(diversLog));
        // Then
        assertEquals(1L, diversLogDtoListReceived.get(0).getId());
        assertEquals(LocalDate.of(2023, 5, 20), diversLogDtoListReceived.get(0).getDateOfDiving());
        assertEquals("Split", diversLogDtoListReceived.get(0).getLocalization());
        assertEquals(5, diversLogDtoListReceived.get(0).getVisibility());
        assertEquals(25, diversLogDtoListReceived.get(0).getAirTemperature());
        assertEquals(25, diversLogDtoListReceived.get(0).getSurfaceTemperature());
        assertEquals(20, diversLogDtoListReceived.get(0).getBottomTemperature());
        assertEquals(5, diversLogDtoListReceived.get(0).getWeight());
        assertEquals(15, diversLogDtoListReceived.get(0).getDepth());
        assertEquals(100, diversLogDtoListReceived.get(0).getTimeOfDiving());
        assertEquals(LocalTime.of(12, 30, 30), diversLogDtoListReceived.get(0).getTimeIn());
        assertEquals(LocalTime.of(13, 40, 30), diversLogDtoListReceived.get(0).getTimeOut());
        assertEquals("Fresh, shore", diversLogDtoListReceived.get(0).getConditions());
        assertEquals(100, diversLogDtoListReceived.get(0).getAirUsed());
        assertEquals(11L, diversLogDtoListReceived.get(0).getUserId());
    }
}
