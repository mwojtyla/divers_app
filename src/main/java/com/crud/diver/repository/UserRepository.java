package com.crud.diver.repository;

import com.crud.diver.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByLoginAndPassword(String login, String password);

    User save(User user);

    void deleteById(Long id);
}
