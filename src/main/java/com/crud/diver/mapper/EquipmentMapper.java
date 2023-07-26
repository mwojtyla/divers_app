package com.crud.diver.mapper;

import com.crud.diver.domain.Equipment;
import com.crud.diver.domain.EquipmentDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentMapper {

    private final UserDbService userDbService;

    public Equipment mapToEquipment(final EquipmentDto equipmentDto) throws UserNotFoundException {
        return Equipment.builder()
                .id(equipmentDto.getId())
                .name(equipmentDto.getName())
                .user(userDbService.getUserById(equipmentDto.getUserId()))
                .build();
    }

    public EquipmentDto mapToEquipmentDto(final Equipment equipment) {
        return EquipmentDto.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .userId(equipment.getUser().getId())
                .build();
    }

    public List<EquipmentDto> mapToEquipmentDtoList(final List<Equipment> equipmentList) {
        return equipmentList.stream()
                .map(this::mapToEquipmentDto)
                .toList();
    }
}
