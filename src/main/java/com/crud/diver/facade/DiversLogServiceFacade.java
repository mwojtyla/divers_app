package com.crud.diver.facade;

import com.crud.diver.domain.DiversLog;
import com.crud.diver.domain.DiversLogDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.DiversLogMapper;
import com.crud.diver.service.DiversLogDbService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiversLogServiceFacade {
    private final DiversLogDbService diversLogDbService;
    private final DiversLogMapper diversLogMapper;
    private final UserDbService userDbService;

    public List<DiversLogDto> getDiversLogsByUserId(Long userId) {
        List<DiversLog> diversLogList = diversLogDbService.getDiversLogsByUserId(userId);
        return diversLogMapper.mapToDiversLogDtoList(diversLogList);
    }

    public void createDiversLog(DiversLogDto diversLogDto) throws UserNotFoundException {
        DiversLog diversLog = diversLogMapper.mapToDiversLog(diversLogDto);
        userDbService.getUserById(diversLogDto.getUserId()).getDiversLogs().add(diversLog);
        diversLogDbService.saveDiversLog(diversLog);
    }

    public void updateDiversLog(DiversLogDto diversLogDto) throws UserNotFoundException {
        DiversLog diversLog = diversLogMapper.mapToDiversLog(diversLogDto);
        DiversLog savedDiversLog = diversLogDbService.saveDiversLog(diversLog);
        diversLogMapper.mapToDiversLogDto(savedDiversLog);
    }

    public void deleteDiversLog(Long diversLogId) {
        diversLogDbService.deleteDiversLog(diversLogId);
    }

    public List<DiversLogDto> getDiversLogByLocalizationAndUserId(String localization, Long userId) {
        List<DiversLog> diversLogList = diversLogDbService.getDiversLogsByUserId(userId).stream()
                .filter(log -> log.getLocalization().contains(localization))
                .collect(Collectors.toList());
        return diversLogMapper.mapToDiversLogDtoList(diversLogList);
    }

    public List<DiversLogDto> getDiversLogByDepthAndUserId(double depth, Long userId) {
        List<DiversLog> diversLogList = diversLogDbService.getDiversLogByDepthAndUserId(depth, userId);
        return diversLogMapper.mapToDiversLogDtoList(diversLogList);
    }
}
