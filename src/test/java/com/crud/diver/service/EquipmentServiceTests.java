package com.crud.diver.service;

import com.crud.diver.domain.Equipment;
import com.crud.diver.domain.User;
import com.crud.diver.repository.EquipmentRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTests {
    @InjectMocks
    private EquipmentDbService equipmentDbService;
    @Mock
    private EquipmentRepository equipmentRepository;

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
                .equipments(new ArrayList<>())
                .build();
    }
    private Equipment createEquipmentData() {
        return Equipment.builder()
                .id(1L)
                .name("Fins")
                .user(createUserData())
                .build();
    }

    @Test
    void shouldGetEquipmentsByUserId() {
        // Given
        Equipment equipment = createEquipmentData();
        User user = createUserData();
        user.getEquipments().add(equipment);
        when(equipmentRepository.findByUserId(user.getId())).thenReturn(List.of(equipment));
        // When
        List<Equipment> equipmentListReceived = equipmentDbService.getEquipmentByUserId(user.getId());
        // Then
        assertEquals(1, equipmentListReceived.size());
        assertEquals(1L, equipmentListReceived.get(0).getId());
        assertEquals("Fins", equipmentListReceived.get(0).getName());
        assertEquals(1L, equipmentListReceived.get(0).getUser().getId());
    }

    @Test
    void shouldSaveEquipment() {
        // Given
        Equipment equipment = createEquipmentData();
        User user = createUserData();
        user.getEquipments().add(equipment);
        when(equipmentRepository.save(equipment)).thenReturn(equipment);
        // When
        Equipment equipmentReceived = equipmentDbService.saveEquipment(equipment);
        // Then
        assertEquals(1L, equipmentReceived.getId());
        assertEquals("Fins", equipmentReceived.getName());
        assertEquals(1L, equipmentReceived.getUser().getId());
    }

    @Test
    void shouldDeleteEquipment() {
        // Given
        Long equipmentId = 1L;
        // When
        equipmentDbService.deleteEquipment(equipmentId);
        // Then
        Mockito.verify(equipmentRepository, times(1)).deleteById(equipmentId);
    }

    @Test
    void shouldGetEquipmentByNameAndUserId() {
        // Given
        Equipment equipment = createEquipmentData();
        User user = createUserData();
        user.getEquipments().add(equipment);
        when(equipmentRepository.findByNameAndUserId(equipment.getName(), user.getId())).thenReturn(Optional.of(equipment));
        // When
        Optional<Equipment> equipmentReceived = equipmentDbService.getEquipmentByNameAndUserId(equipment.getName(),user.getId());
        // Then
        assertEquals(1L, equipmentReceived.get().getId());
        assertEquals("Fins", equipmentReceived.get().getName());
    }
}
