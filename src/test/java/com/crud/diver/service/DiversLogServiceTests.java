package com.crud.diver.service;

import com.crud.diver.domain.DiversLog;
import com.crud.diver.domain.User;
import com.crud.diver.repository.DiversLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class DiversLogServiceTests {

    @InjectMocks
    private DiversLogDbService diversLogDbService;
    @Mock
    private DiversLogRepository diversLogRepository;

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
                .diversLogs(new ArrayList<>())
                .build();
    }

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
                .user(createUserData())
                .build();
    }

    @Test
    void shouldGetDiversLogByUserId() {
        // Given
        DiversLog diversLog = createDiversLogData();
        User user = createUserData();
        user.getDiversLogs().add(diversLog);
        when(diversLogRepository.findByUserId(user.getId())).thenReturn(List.of(diversLog));
        // When
        List<DiversLog> diversLogListReceived = diversLogDbService.getDiversLogsByUserId(user.getId());
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
        assertEquals(1L, diversLogListReceived.get(0).getUser().getId());
    }

    @Test
    void shouldGetDiversLogByDepthAndUserId() {
        // Given
        DiversLog diversLog = createDiversLogData();
        User user = createUserData();
        user.getDiversLogs().add(diversLog);
        when(diversLogRepository.findByDepthAndUserId(diversLog.getDepth(), user.getId())).thenReturn(List.of(diversLog));
        // When
        List<DiversLog> diversLogListReceived = diversLogDbService.getDiversLogByDepthAndUserId(diversLog.getDepth(), user.getId());
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
        assertEquals(1L, diversLogListReceived.get(0).getUser().getId());
    }

    @Test
    void shouldSaveDiversLog() {
        // Given
        DiversLog diversLog = createDiversLogData();
        User user = createUserData();
        user.getDiversLogs().add(diversLog);
        when(diversLogRepository.save(diversLog)).thenReturn(diversLog);
        // When
        DiversLog diversLogReceived = diversLogDbService.saveDiversLog(diversLog);
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
        assertEquals(1L, diversLogReceived.getUser().getId());
    }

    @Test
    void shouldDeleteDiversLog() {
        // Given
        Long diversLogId = 1L;
        // When
        diversLogDbService.deleteDiversLog(diversLogId);
        // Then
        Mockito.verify(diversLogRepository, times(1)).deleteById(diversLogId);
    }
}
