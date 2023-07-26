package com.crud.diver.facade;

import com.crud.diver.domain.DivingBase;
import com.crud.diver.domain.DivingBaseDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.DivingBaseMapper;
import com.crud.diver.service.DivingBaseDbService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DivingBaseServiceFacade {
    private final DivingBaseDbService divingBaseDbService;
    private final UserDbService userDbService;
    private final DivingBaseMapper divingBaseMapper;

    public List<DivingBaseDto> getDivingBasesByUserId(Long userId) {
        List<DivingBase> divingBaseList = divingBaseDbService.getDivingBasesByUserId(userId);
        return divingBaseMapper.mapToDivingBaseDtoList(divingBaseList);
    }

    public void createDivingBase(DivingBaseDto divingBaseDto) throws UserNotFoundException {
        DivingBase divingBase = divingBaseMapper.mapToDivingBase(divingBaseDto);
        userDbService.getUserById(divingBaseDto.getUserId()).getDivingBases().add(divingBase);
        divingBaseDbService.saveDivingBase(divingBase);
    }

    public void updateDivingBase(DivingBaseDto divingBaseDto) throws UserNotFoundException {
        DivingBase divingBase = divingBaseMapper.mapToDivingBase(divingBaseDto);
        DivingBase savedDivingBase = divingBaseDbService.saveDivingBase(divingBase);
        divingBaseMapper.mapToDivingBaseDto(savedDivingBase);
    }

    public void deleteDivingBase(Long divingBaseId) {
        divingBaseDbService.deleteDivingBase(divingBaseId);
    }
}
