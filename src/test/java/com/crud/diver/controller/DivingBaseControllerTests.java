package com.crud.diver.controller;

import com.crud.diver.domain.DivingBase;
import com.crud.diver.domain.DivingBaseDto;
import com.crud.diver.domain.User;
import com.crud.diver.facade.DivingBaseServiceFacade;
import com.google.gson.Gson;
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
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DivingBaseController.class)
public class DivingBaseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DivingBaseServiceFacade divingBaseServiceFacade;

    private DivingBaseDto createDivingBaseDto(){
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
    void shouldGetListOfDivingBaseByUserId() throws Exception {
        // Given
        DivingBaseDto divingBaseDto = createDivingBaseDto();
        List<DivingBaseDto> divingBaseDtoList = List.of(divingBaseDto);

        when(divingBaseServiceFacade.getDivingBasesByUserId(11L)).thenReturn(divingBaseDtoList);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/divingbase/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Sun")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization", Matchers.is("Split")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyName", Matchers.is("EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyRate", Matchers.is(5.1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperature", Matchers.is(10.1)));
    }

    @Test
    void shouldGetEmptyListOfDivingBaseByUserId() throws Exception {
        // Given
        when(divingBaseServiceFacade.getDivingBasesByUserId(11L)).thenReturn(List.of());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/divingbase/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldCreateDivingBase() throws Exception {
        // Given
        DivingBaseDto divingBaseDto = createDivingBaseDto();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(divingBaseDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/diver/divingbase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(divingBaseServiceFacade, times(1)).createDivingBase(divingBaseDto);
    }

    @Test
    void shouldUpdateDivingBase() throws Exception {
        // Given
        DivingBaseDto divingBaseDto = createDivingBaseDto();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(divingBaseDto);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/diver/divingbase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(divingBaseServiceFacade, times(1)).updateDivingBase(divingBaseDto);
    }

    @Test
    void shouldDeleteDivingBase() throws Exception {
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

        DivingBase divingBase = DivingBase.builder()
                .id(1L)
                .name("Sun")
                .localization("Split")
                .currencyName("EUR")
                .currencyRate(5.1)
                .temperature(10.1)
                .user(user)
                .build();
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/diver/divingbase/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(divingBaseServiceFacade, times(1)).deleteDivingBase(divingBase.getId());
    }
}
