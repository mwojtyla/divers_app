package com.crud.diver.repository;


import com.crud.diver.domain.DiversLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DiversLogRepository extends CrudRepository<DiversLog, Long> {

    List<DiversLog> findByUserId(Long userId);

    Optional<DiversLog> findById(Long id);

    List<DiversLog> findByDepthAndUserId(double depth, Long userId);

    DiversLog save(DiversLog diversLog);

    void deleteById(Long id);
}
