package com.crud.diver.repository;

import com.crud.diver.domain.DivingBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DivingBaseRepository extends CrudRepository<DivingBase, Long> {

    List<DivingBase> findByUserId(Long userId);

    Optional<DivingBase> findById(Long Id);

    DivingBase save(DivingBase divingBase);

    void deleteById(Long id);
}
