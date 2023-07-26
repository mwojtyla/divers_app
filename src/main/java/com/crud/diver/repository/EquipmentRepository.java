package com.crud.diver.repository;

import com.crud.diver.domain.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    List<Equipment> findByUserId(Long userId);

    Optional<Equipment> findByNameAndUserId(String name, Long userId);

    Equipment save(Equipment equipment);

    void deleteById(Long id);
}
