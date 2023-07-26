package com.crud.diver.controller;

import com.crud.diver.domain.*;
import com.crud.diver.facade.EquipmentServiceFacade;
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
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentServiceFacade equipmentServiceFacade;

    private EquipmentDto createEquipmentDto(){
        return EquipmentDto.builder()
                .id(1L)
                .name("Fins")
                .userId(11L)
                .build();
    }

    @Test
    void shouldGetListOfEquipmentByUserId() throws Exception {
        // Given
        EquipmentDto equipmentDto = createEquipmentDto();
        List<EquipmentDto> equipmentDtosList = List.of(equipmentDto);

        when(equipmentServiceFacade.getEquipmentsByUserId(11L)).thenReturn(equipmentDtosList);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/equipment/getEquipmentsByUserId/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Fins")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(11)));
    }

    @Test
    void shouldGetEmptyListOfEquipmentsByUserId() throws Exception {
        // Given
        when(equipmentServiceFacade.getEquipmentsByUserId(11L)).thenReturn(List.of());

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/equipment/getEquipmentsByUserId/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetEquipmentIdByNameAndUserId() throws Exception {
        // Given
        EquipmentDto equipmentDto = createEquipmentDto();

        when(equipmentServiceFacade.getEquipmentIdByNameAndUserId(equipmentDto.getName(), equipmentDto.getUserId())).thenReturn(equipmentDto.getId());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/equipment/getEquipmentId/Fins/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void shouldCreateEquipment() throws Exception {
        // Given
        EquipmentDto equipmentDto = createEquipmentDto();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(equipmentDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/diver/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(equipmentServiceFacade, times(1)).createEquipment(equipmentDto);
    }

    @Test
    void shouldUpdateEquipment() throws Exception {
        // Given
        EquipmentDto equipmentDto = createEquipmentDto();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(equipmentDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/diver/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept("application/json")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(equipmentServiceFacade, times(1)).updateEquipment(equipmentDto);
    }

    @Test
    void shouldDeleteEquipment() throws Exception {
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

        Equipment equipment = Equipment.builder()
                .id(1L)
                .name("Fins")
                .user(user)
                .build();
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/diver/equipment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(equipmentServiceFacade, times(1)).deleteEquipment(equipment.getId());
    }
}


