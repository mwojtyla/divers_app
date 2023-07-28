package com.crud.diver.facade;

import com.crud.diver.domain.User;
import com.crud.diver.domain.UserDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.UserMapper;
import com.crud.diver.service.DiversLogDbService;
import com.crud.diver.service.DivingBaseDbService;
import com.crud.diver.service.EquipmentDbService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceFacade {
    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final EquipmentDbService equipmentDbService;
    private final DiversLogDbService diversLogDbService;

    private final DivingBaseDbService divingBaseDbService;

    public boolean isUserAuthorised(String login, String password) {
        try {
            Optional<User> user = userDbService.getUserByLoginAndPassword(login, password);
            if (user.get().getLogin().equals(login) && user.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public UserDto getUser(Long userId) throws UserNotFoundException {
        User user = userDbService.getUserById(userId);
        return userMapper.mapToUserDto(user);
    }

    public void createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
    }

    public void updateUser(UserDto userDto) throws UserNotFoundException {
        User user = userDbService.getUserById(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setLocalization(userDto.getLocalization());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userDbService.saveUser(user);
    }

    public void deleteUser(Long userId) {
        List<Long> diversLogIdList = diversLogDbService.getDiversLogsByUserId(userId).stream()
                .map(i -> i.getId())
                .toList();
        for (int i = 0; i < diversLogIdList.size(); i++) {
            diversLogDbService.deleteDiversLog(diversLogIdList.get(i));
        }

        List<Long> equipmentIdList = equipmentDbService.getEquipmentByUserId(userId).stream()
                .map(i -> i.getId())
                .toList();
        for (int i = 0; i < equipmentIdList.size(); i++) {
            equipmentDbService.deleteEquipment(equipmentIdList.get(i));
        }

        List<Long> divingBaseIdList = divingBaseDbService.getDivingBasesByUserId(userId).stream()
                .map(i -> i.getId())
                .toList();
        for (int i = 0; i < divingBaseIdList.size(); i++) {
            divingBaseDbService.deleteDivingBase(divingBaseIdList.get(i));
        }

        userDbService.deleteUser(userId);
    }

    public Long getUserIdByLoginAndPassword(String login, String password) {
        Optional<User> user = userDbService.getUserByLoginAndPassword(login, password);
        return user.get().getId();
    }

    public boolean isLoginAvailable(String login) {
         List<User> users = userDbService.getAllUsers();
         long amountOfLogin = users.stream()
                 .filter(u->u.getLogin().equals(login))
                 .count();
         if (amountOfLogin == 0) {
                return true;
            } else {
             return false;
         }
    }
}


