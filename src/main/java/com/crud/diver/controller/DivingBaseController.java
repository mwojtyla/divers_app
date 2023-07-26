package com.crud.diver.controller;

import com.crud.diver.domain.*;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.facade.DivingBaseServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/diver/divingbase")
@RequiredArgsConstructor
public class DivingBaseController {
    private final DivingBaseServiceFacade divingBaseServiceFacade;

    @GetMapping("{userId}")
    public ResponseEntity<List<DivingBaseDto>> getDivingBasesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(divingBaseServiceFacade.getDivingBasesByUserId(userId));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDivingBase(@RequestBody DivingBaseDto divingBaseDto) throws UserNotFoundException {
        divingBaseServiceFacade.createDivingBase(divingBaseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateDivingBase(@RequestBody DivingBaseDto divingBaseDto) throws UserNotFoundException {
        divingBaseServiceFacade.updateDivingBase(divingBaseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{divingBaseId}")
    public ResponseEntity<Void> deleteDivingBase(@PathVariable Long divingBaseId) {
        divingBaseServiceFacade.deleteDivingBase(divingBaseId);
        return ResponseEntity.ok().build();
    }
}
