package com.crud.diver.facade;

import com.crud.diver.domain.Equipment;
import com.crud.diver.domain.EquipmentDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.EquipmentMapper;
import com.crud.diver.service.EquipmentDbService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EquipmentServiceFacade {
    private final EquipmentDbService equipmentDbService;
    private final EquipmentMapper equipmentMapper;
    private final UserDbService userDbService;

    public List<EquipmentDto> getEquipmentsByUserId(Long userId) {
        List<Equipment> equipmentList = equipmentDbService.getEquipmentByUserId(userId);
        return equipmentMapper.mapToEquipmentDtoList(equipmentList);
    }

    public void createEquipment(EquipmentDto equipmentDto) throws UserNotFoundException {
        Equipment equipment = equipmentMapper.mapToEquipment(equipmentDto);
        userDbService.getUserById(equipmentDto.getUserId()).getEquipments().add(equipment);
        equipmentDbService.saveEquipment(equipment);
    }

    public void updateEquipment(EquipmentDto equipmentDto) throws UserNotFoundException {
        Equipment equipment = equipmentMapper.mapToEquipment(equipmentDto);
        Equipment savedEquipment = equipmentDbService.saveEquipment(equipment);
        equipmentMapper.mapToEquipmentDto(savedEquipment);
    }

    public void deleteEquipment(Long equipmentId) {
        equipmentDbService.deleteEquipment(equipmentId);
    }

    public Long getEquipmentIdByNameAndUserId(String name, Long userId) {
        Optional<Equipment> equipment = equipmentDbService.getEquipmentIdByNameAndUserId(name, userId);
        return equipment.get().getId();
    }
}
