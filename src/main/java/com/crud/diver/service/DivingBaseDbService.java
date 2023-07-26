package com.crud.diver.service;

import com.crud.diver.domain.DivingBase;
import com.crud.diver.repository.DivingBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivingBaseDbService {

    private final DivingBaseRepository divingBaseRepository;
    public List<DivingBase> getDivingBasesByUserId(final Long userId) {
        return divingBaseRepository.findByUserId(userId);
    }
    public DivingBase saveDivingBase(final DivingBase divingBase) {
        return divingBaseRepository.save(divingBase);
    }
    public void deleteDivingBase(final Long id) {
        divingBaseRepository.deleteById(id);
    }
}
