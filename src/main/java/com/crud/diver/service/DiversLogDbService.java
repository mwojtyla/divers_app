package com.crud.diver.service;

import com.crud.diver.domain.DiversLog;
import com.crud.diver.repository.DiversLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiversLogDbService {

    private final DiversLogRepository diversLogRepository;

    public List<DiversLog> getDiversLogsByUserId(final Long userId) {
        return diversLogRepository.findByUserId(userId);
    }

    public List<DiversLog> getDiversLogByDepthAndUserId(final double depth, final Long userId) {
        return diversLogRepository.findByDepthAndUserId(depth, userId);
    }

    public DiversLog saveDiversLog(final DiversLog diversLog) {
        return diversLogRepository.save(diversLog);
    }

    public void deleteDiversLog(final Long id) {
        diversLogRepository.deleteById(id);
    }
}
