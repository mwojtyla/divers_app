package com.crud.diver.mapper;

import com.crud.diver.currency.client.CurrencyExchangeClient;
import com.crud.diver.domain.DivingBase;
import com.crud.diver.domain.DivingBaseDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.service.UserDbService;
import com.crud.diver.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DivingBaseMapper {
    private final UserDbService userDbService;
    private final CurrencyExchangeClient currencyExchangeClient;
    private final WeatherClient weatherClient;

    public DivingBase mapToDivingBase(DivingBaseDto divingBaseDto) throws UserNotFoundException {
        return DivingBase.builder()
                .id(divingBaseDto.getId())
                .name(divingBaseDto.getName())
                .localization(divingBaseDto.getLocalization())
                .currencyName(divingBaseDto.getCurrencyName())
                .currencyRate(currencyExchangeClient.getCurrencyExchangeRate(divingBaseDto.getCurrencyName()))
                .temperature(weatherClient.getTemperature(divingBaseDto.getLocalization()))
                .user(userDbService.getUserById(divingBaseDto.getUserId()))
                .build();
    }

    public DivingBaseDto mapToDivingBaseDto(DivingBase divingBase) {
        return DivingBaseDto.builder()
                .id(divingBase.getId())
                .name(divingBase.getName())
                .localization(divingBase.getLocalization())
                .currencyName(divingBase.getCurrencyName())
                .currencyRate(currencyExchangeClient.getCurrencyExchangeRate(divingBase.getCurrencyName()))
                .temperature(weatherClient.getTemperature(divingBase.getLocalization()))
                .userId(divingBase.getUser().getId())
                .build();
    }

    public List<DivingBaseDto> mapToDivingBaseDtoList(List<DivingBase> divingBaseList) {
        return divingBaseList.stream()
                .map(this::mapToDivingBaseDto)
                .collect(Collectors.toList());
    }
}
