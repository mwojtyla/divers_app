package com.crud.diver.service;

import com.crud.diver.domain.Equipment;
import com.crud.diver.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentDbService {

    private final EquipmentRepository equipmentRepository;

    public List<Equipment> getEquipmentByUserId(final Long userId) {
        return equipmentRepository.findByUserId(userId);
    }

    public Equipment saveEquipment(final Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(final Long id) {
        equipmentRepository.deleteById(id);
    }

    public Optional<Equipment> getEquipmentIdByNameAndUserId(final String name, final Long userId) {
        return equipmentRepository.findByNameAndUserId(name, userId);
    }
}
