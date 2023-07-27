package com.crud.diver.facade;


import com.crud.diver.domain.Equipment;
import com.crud.diver.domain.EquipmentDto;
import com.crud.diver.domain.User;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.EquipmentMapper;
import com.crud.diver.service.EquipmentDbService;
import com.crud.diver.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceFacadeTests {
    @InjectMocks
    private EquipmentServiceFacade equipmentServiceFacade;

    @Mock
    private EquipmentMapper equipmentMapper;

    @Mock
    private EquipmentDbService equipmentDbService;

    @Mock
    private UserDbService userDbService;
    private Equipment createEquipmentData() {
        return Equipment.builder()
                .id(1L)
                .name("Fins")
                .user(new User())
                .build();
    }

    private EquipmentDto createEquipmentDtoData() {
        return EquipmentDto.builder()
                .id(1L)
                .name("Fins")
                .userId(1L)
                .build();
    }

    @Test
    void shouldGetListOfEquipmentsByUserId() {
        // Given
        Equipment equipment = createEquipmentData();
        EquipmentDto equipmentDto = createEquipmentDtoData();
        when(equipmentDbService.getEquipmentByUserId(1L)).thenReturn(List.of(equipment));
        when(equipmentMapper.mapToEquipmentDtoList(List.of(equipment))).thenReturn(List.of(equipmentDto));
        // When
        List<EquipmentDto> equipmentListReceived = equipmentServiceFacade.getEquipmentsByUserId(1L);
        // Then
        assertEquals(1, equipmentListReceived.size());
        assertEquals(1L, equipmentListReceived.get(0).getId());
        assertEquals("Fins", equipmentListReceived.get(0).getName());
        assertEquals(1L, equipmentListReceived.get(0).getUserId());
    }

    @Test
    void shouldCreateEquipment() throws UserNotFoundException {
        // Given
        Equipment equipment = createEquipmentData();
        EquipmentDto equipmentDto = createEquipmentDtoData();

        when(equipmentMapper.mapToEquipment(equipmentDto)).thenReturn(equipment);
        when(userDbService.getUserById(1L)).thenReturn(new User());
        when(equipmentDbService.saveEquipment(equipment)).thenReturn(equipment);
        // When
        equipmentServiceFacade.createEquipment(equipmentDto);
        // Then
        Mockito.verify(equipmentMapper, times(1)).mapToEquipment(equipmentDto);
        Mockito.verify(userDbService, times(1)).getUserById(1L);
        Mockito.verify(equipmentDbService, times(1)).saveEquipment(equipment);
    }

    @Test
    void shouldUpdateEquipment() throws UserNotFoundException {
        // Given
        Equipment equipment = createEquipmentData();
        EquipmentDto equipmentDto = createEquipmentDtoData();

        when(equipmentMapper.mapToEquipment(equipmentDto)).thenReturn(equipment);
        when(equipmentDbService.saveEquipment(equipment)).thenReturn(equipment);
        when(equipmentMapper.mapToEquipmentDto(equipment)).thenReturn(equipmentDto);
        // When
        equipmentServiceFacade.updateEquipment(equipmentDto);
        // Then
        Mockito.verify(equipmentMapper, times(1)).mapToEquipment(equipmentDto);
        Mockito.verify(equipmentDbService, times(1)).saveEquipment(equipment);
        Mockito.verify(equipmentMapper, times(1)).mapToEquipmentDto(equipment);
    }

    @Test
    void shouldDeleteEquipment() {
        // Given
        Equipment equipment = createEquipmentData();
        // When
        equipmentServiceFacade.deleteEquipment(equipment.getId());
        // Then
        Mockito.verify(equipmentDbService, times(1)).deleteEquipment(equipment.getId());
    }

    @Test
    void shouldGetEquipmentIdByNameAndUserId() {
        // Given
        Equipment equipment = createEquipmentData();
        when(equipmentDbService.getEquipmentByNameAndUserId(equipment.getName(), 1L)).thenReturn(Optional.of(equipment));
        // When
        Long equipmentIdReceived = equipmentServiceFacade.getEquipmentIdByNameAndUserId(equipment.getName(), 1L);
        // Then
        assertEquals(1, equipmentIdReceived);
    }

}
