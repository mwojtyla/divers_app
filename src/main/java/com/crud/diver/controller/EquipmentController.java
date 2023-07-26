package com.crud.diver.controller;

import com.crud.diver.domain.EquipmentDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.facade.EquipmentServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/diver/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentServiceFacade equipmentServiceFacade;

    @GetMapping("getEquipmentsByUserId/{userId}")
    public ResponseEntity<List<EquipmentDto>> getEquipmentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(equipmentServiceFacade.getEquipmentsByUserId(userId));
    }

    @GetMapping("getEquipmentId/{name}/{userId}")
    public ResponseEntity<Long> getEquipmentIdByNameAndUserId(@PathVariable String name, @PathVariable Long userId) {
        return ResponseEntity.ok(equipmentServiceFacade.getEquipmentIdByNameAndUserId(name, userId));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentDto equipmentDto) throws UserNotFoundException {
        equipmentServiceFacade.createEquipment(equipmentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEquipment(@RequestBody EquipmentDto equipmentDto) throws UserNotFoundException {
        equipmentServiceFacade.updateEquipment(equipmentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long equipmentId) {
        equipmentServiceFacade.deleteEquipment(equipmentId);
        return ResponseEntity.ok().build();
    }
}
