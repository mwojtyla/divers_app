package com.crud.diver.mapper;

import com.crud.diver.domain.Equipment;
import com.crud.diver.domain.EquipmentDto;
import com.crud.diver.domain.User;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.UserDbService;
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
public class EquipmentMapperTests {
    @InjectMocks
    private EquipmentMapper equipmentMapper;
    @Mock
    private UserDbService userDbService;

    private User createUserData(){
        return User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .equipments(new ArrayList<>())
                .build();
    }
    private Equipment createEquipmentData(){
        return Equipment.builder()
                .id(1L)
                .name("Fins")
                .user(createUserData())
                .build();
    }

    private EquipmentDto createEquipmentDtoData(){
        return EquipmentDto.builder()
                .id(1L)
                .name("Fins")
                .userId(1L)
                .build();
    }
    @Test
    void shouldReturnEquipmentWhenMapEquipmentDtoToEquipment() throws UserNotFoundException {
        // Given
        User user = createUserData();
        Equipment equipment = createEquipmentData();
        user.getEquipments().add(equipment);
        EquipmentDto equipmentDto = createEquipmentDtoData();

        when(userDbService.getUserById(equipmentDto.getUserId())).thenReturn(user);
        // When
        Equipment equipmentReceived = equipmentMapper.mapToEquipment(equipmentDto);
        // Then
        assertEquals(1L, equipmentReceived.getId());
        assertEquals("Fins", equipmentReceived.getName());
        assertEquals(1L, equipmentReceived.getUser().getId());
    }

    @Test
    void shouldReturnEquipmentDtoWhenMapEquipmentToEquipmentDto() {
        // Given
        User user = createUserData();
        Equipment equipment = createEquipmentData();
        user.getEquipments().add(equipment);
        // When
        EquipmentDto equipmentDtoReceived = equipmentMapper.mapToEquipmentDto(equipment);
        // Then
        assertEquals(1L, equipmentDtoReceived.getId());
        assertEquals("Fins", equipmentDtoReceived.getName());
        assertEquals(1L, equipmentDtoReceived.getUserId());
    }


    @Test
    void shouldReturnEquipmentDtoListWhenMapEquipmentListToEquipmentDtoList() {
        // Given
        User user = createUserData();
        Equipment equipment = createEquipmentData();
        user.getEquipments().add(equipment);
        // When
        List<EquipmentDto> equipmentDtoListReceived = equipmentMapper.mapToEquipmentDtoList(List.of(equipment));
        // Then
        assertEquals(1, equipmentDtoListReceived.size());
        assertEquals(1L, equipmentDtoListReceived.get(0).getId());
        assertEquals("Fins", equipmentDtoListReceived.get(0).getName());
        assertEquals(1L, equipmentDtoListReceived.get(0).getUserId());
    }
}
