package com.crud.diver.mapper;

import com.crud.diver.domain.DiversLog;
import com.crud.diver.domain.DiversLogDto;
import com.crud.diver.exception.DivingBaseNotFoundException;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.DivingBaseDbService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiversLogMapper {

    private final UserDbService userDbService;

    public DiversLog mapToDiversLog(DiversLogDto diversLogDto) throws UserNotFoundException {
        return DiversLog.builder()
                .id(diversLogDto.getId())
                .dateOfDiving(diversLogDto.getDateOfDiving())
                .localization(diversLogDto.getLocalization())
                .visibility(diversLogDto.getVisibility())
                .airTemperature(diversLogDto.getAirTemperature())
                .surfaceTemperature(diversLogDto.getSurfaceTemperature())
                .bottomTemperature(diversLogDto.getBottomTemperature())
                .weight(diversLogDto.getWeight())
                .depth(diversLogDto.getDepth())
                .timeOfDiving(diversLogDto.getTimeOfDiving())
                .timeIn(diversLogDto.getTimeIn())
                .timeOut(diversLogDto.getTimeOut())
                .conditions(diversLogDto.getConditions())
                .airUsed(diversLogDto.getAirUsed())
                .user(userDbService.getUserById(diversLogDto.getUserId()))
                .build();
    }

    public DiversLogDto mapToDiversLogDto(DiversLog diversLog) {
        return new DiversLogDto.DiversLogDtoBuilder()
                .id(diversLog.getId())
                .dateOfDiving(diversLog.getDateOfDiving())
                .localization(diversLog.getLocalization())
                .visibility(diversLog.getVisibility())
                .airTemperature(diversLog.getAirTemperature())
                .surfaceTemperature(diversLog.getSurfaceTemperature())
                .bottomTemperature(diversLog.getBottomTemperature())
                .weight(diversLog.getWeight())
                .depth(diversLog.getDepth())
                .timeOfDiving(diversLog.getTimeOfDiving())
                .timeIn(diversLog.getTimeIn())
                .timeOut(diversLog.getTimeOut())
                .conditions(diversLog.getConditions())
                .airUsed(diversLog.getAirUsed())
                .userId(diversLog.getUser().getId())
                .build();
    }

    public List<DiversLogDto> mapToDiversLogDtoList(List<DiversLog> diversLogList) {
        return diversLogList.stream()
                .map(this::mapToDiversLogDto)
                .collect(Collectors.toList());
    }
}
