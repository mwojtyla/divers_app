package com.crud.diver.controller;

import com.crud.diver.domain.DiversLogDto;
import com.crud.diver.exception.DivingBaseNotFoundException;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.facade.DiversLogServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/diver/diverslog")
@RequiredArgsConstructor
public class DiversLogController {

    private final DiversLogServiceFacade diversLogServiceFacade;

    @GetMapping({"{userId}"})
    public ResponseEntity<List<DiversLogDto>> getDiversLogsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(diversLogServiceFacade.getDiversLogsByUserId(userId));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDiversLog(@RequestBody DiversLogDto diversLogDto) throws UserNotFoundException, DivingBaseNotFoundException {
        diversLogServiceFacade.createDiversLog(diversLogDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateDiversLog(@RequestBody DiversLogDto diversLogDto) throws UserNotFoundException, DivingBaseNotFoundException {
        diversLogServiceFacade.updateDiversLog(diversLogDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{diversLogId}")
    public ResponseEntity<Void> deleteDiversLog(@PathVariable Long diversLogId) {
        diversLogServiceFacade.deleteDiversLog(diversLogId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/localization/{localization}/{userId}")
    public ResponseEntity<List<DiversLogDto>> getDiversLogByLocalizationAndUserId(@PathVariable String localization, @PathVariable Long userId) {
        return ResponseEntity.ok(diversLogServiceFacade.getDiversLogByLocalizationAndUserId(localization, userId));
    }

    @GetMapping("/depth/{depth}/{userId}")
    public ResponseEntity<List<DiversLogDto>> getDiversLogByDepthAndUserId(@PathVariable double depth, @PathVariable Long userId) {
        return ResponseEntity.ok(diversLogServiceFacade.getDiversLogByDepthAndUserId(depth, userId));
    }
}
