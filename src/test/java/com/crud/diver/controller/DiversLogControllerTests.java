package com.crud.diver.controller;

import com.crud.diver.config.LocalDateAdapter;
import com.crud.diver.config.LocalTimeAdapter;
import com.crud.diver.domain.*;
import com.crud.diver.facade.DiversLogServiceFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DiversLogController.class)
public class DiversLogControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiversLogServiceFacade diversLogServiceFacade;

    private DiversLogDto createDiversLogDto() {
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
    void shouldGetListOfDiversLogByUserId() throws Exception {
        // Given
        DiversLogDto diversLogDto = createDiversLogDto();
        List<DiversLogDto> diversLogDtoList = List.of(diversLogDto);

        when(diversLogServiceFacade.getDiversLogsByUserId(11L)).thenReturn(diversLogDtoList);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/diverslog/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfDiving", Matchers.is(String.valueOf(LocalDate.of(2023, 5, 20)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization", Matchers.is("Split")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surfaceTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bottomTemperature", Matchers.is(20.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].depth", Matchers.is(15.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOfDiving", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeIn", Matchers.is(String.valueOf(LocalTime.of(12, 30,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOut", Matchers.is(String.valueOf(LocalTime.of(13, 40,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].conditions", Matchers.is("Fresh, shore")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airUsed", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(11)));
    }

    @Test
    void shouldGetEmptyListOfDiversLogs() throws Exception {
        // Given
        when(diversLogServiceFacade.getDiversLogsByUserId(11L)).thenReturn(List.of());

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/diverslog/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetDiversLogByLocalizationAndUserId() throws Exception {
        // Given
        DiversLogDto diversLogDto = createDiversLogDto();
        List<DiversLogDto> diversLogDtoList = List.of(diversLogDto);

        when(diversLogServiceFacade.getDiversLogByLocalizationAndUserId("Split", 11L)).thenReturn(diversLogDtoList);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/diverslog/localization/Split/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfDiving", Matchers.is(String.valueOf(LocalDate.of(2023, 5, 20)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization", Matchers.is("Split")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surfaceTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bottomTemperature", Matchers.is(20.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].depth", Matchers.is(15.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOfDiving", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeIn", Matchers.is(String.valueOf(LocalTime.of(12, 30,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOut", Matchers.is(String.valueOf(LocalTime.of(13, 40,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].conditions", Matchers.is("Fresh, shore")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airUsed", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(11)));
    }

    @Test
    void shouldGetDiversLogByDepthAndUserId() throws Exception {
        // Given
        DiversLogDto diversLogDto = createDiversLogDto();
        List<DiversLogDto> diversLogDtoList = List.of(diversLogDto);

        when(diversLogServiceFacade.getDiversLogByDepthAndUserId(15, 11L)).thenReturn(diversLogDtoList);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/diverslog/depth/15/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfDiving", Matchers.is(String.valueOf(LocalDate.of(2023, 5, 20)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization", Matchers.is("Split")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].visibility", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surfaceTemperature", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bottomTemperature", Matchers.is(20.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].depth", Matchers.is(15.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOfDiving", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeIn", Matchers.is(String.valueOf(LocalTime.of(12, 30,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeOut", Matchers.is(String.valueOf(LocalTime.of(13, 40,30)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].conditions", Matchers.is("Fresh, shore")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].airUsed", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(11)));
    }

    @Test
    void shouldCreateDiversLog() throws Exception {
        // Given
        DiversLogDto diversLogDto = createDiversLogDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        String jsonContent = gson.toJson(diversLogDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/diver/diverslog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(diversLogServiceFacade, times(1)).createDiversLog(diversLogDto);
    }

    @Test
    void shouldUpdateDiversLog() throws Exception {
        // Given
        DiversLogDto diversLogDto = createDiversLogDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        String jsonContent = gson.toJson(diversLogDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/diver/diverslog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(diversLogServiceFacade, times(1)).updateDiversLog(diversLogDto);

    }

    @Test
    void shouldDeleteDiversLog() throws Exception {
        // Given
        User user = User.builder()
                .id(11L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();

        DiversLog diversLog = DiversLog.builder()
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
                .timeIn(LocalTime.of(12, 30))
                .timeOut(LocalTime.of(13, 40))
                .conditions("Fresh, shore")
                .airUsed(100)
                .user(user)
                .build();

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/diver/diverslog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(diversLogServiceFacade, times(1)).deleteDiversLog(diversLog.getId());
    }
}
