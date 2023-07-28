package com.crud.diver.facade;

import com.crud.diver.domain.User;
import com.crud.diver.domain.UserDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.mapper.UserMapper;
import com.crud.diver.service.DiversLogDbService;
import com.crud.diver.service.DivingBaseDbService;
import com.crud.diver.service.EquipmentDbService;
import com.crud.diver.service.UserDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceFacadeTests {
    @InjectMocks
    private UserServiceFacade userServiceFacade;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDbService userDbService;

    @Mock
    private DivingBaseDbService divingBaseDbService;

    @Mock
    private DiversLogDbService diversLogDbService;

    @Mock
    private EquipmentDbService equipmentDbService;

    private User createUserData(){
        return User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
    }

    private UserDto createUserDtoData(){
        return UserDto.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
    }

    @Test
    void shouldReturnTrueWhenUserIsAuthorised(){
        // Given
        User user = createUserData();
        String login = "lilyevans";
        String password = "lily123";
        when(userDbService.getUserByLoginAndPassword(login, password)).thenReturn(Optional.ofNullable(user));
        // When
        boolean valueReceived = userServiceFacade.isUserAuthorised(login, password);
        // Then
        assertTrue(valueReceived);
    }

    @Test
    void shouldReturnFalseWhenLoginIsNotAvailable(){
        // Given
        User user = createUserData();
        String login = "lilyevans";
        when(userDbService.getAllUsers()).thenReturn(List.of(user));
        // When
        boolean valueReceived = userServiceFacade.isLoginAvailable(login);
        // Then
        assertFalse(valueReceived);
    }

    @Test
    void shouldGetUser() throws UserNotFoundException {
        // Given
        User user = createUserData();
        UserDto userDto = createUserDtoData();
        when(userDbService.getUserById(user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        // When
        UserDto userDtoReceived = userServiceFacade.getUser(user.getId());
        // Then
        assertEquals(1L, userDtoReceived.getId());
        assertEquals("Lily", userDtoReceived.getName());
        assertEquals(LocalDate.of(2020,1,1), userDtoReceived.getDateOfBirth());

    }

    @Test
    void shouldCreateUser() throws UserNotFoundException {
        // Given
        User user = User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
        UserDto userDto = new UserDto(1L, "Lily", "Evans", LocalDate.of(2020,1,1) ,"London", "lily@gmail.com","lilyevans", "lily123");
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userDbService.saveUser(user)).thenReturn(user);
        // When
        userServiceFacade.createUser(userDto);
        // Then
        Mockito.verify(userMapper, times(1)).mapToUser(userDto);
        Mockito.verify(userDbService, times(1)).saveUser(user);

    }

    @Test
    void shouldUpdateUser() throws UserNotFoundException {
        // Given
        User user = User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020,1,1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
        UserDto userDto = new UserDto(1L, "Lily", "Evans", LocalDate.of(2020,1,1) ,"London", "lily@gmail.com","lilyevans", "lily123");
        when(userDbService.getUserById(userDto.getId())).thenReturn(user);
        when(userDbService.saveUser(user)).thenReturn(user);
        // When
        userServiceFacade.updateUser(userDto);
        // Then
        Mockito.verify(userDbService, times(1)).getUserById(userDto.getId());
        Mockito.verify(userDbService, times(1)).saveUser(user);
    }

    @Test
    void shouldDeleteUser(){
        // Given
        User user = createUserData();

        when(diversLogDbService.getDiversLogsByUserId(user.getId())).thenReturn(new ArrayList<>());
        when(equipmentDbService.getEquipmentByUserId(user.getId())).thenReturn(new ArrayList<>());
        when(divingBaseDbService.getDivingBasesByUserId(user.getId())).thenReturn(new ArrayList<>());
        // When
        userServiceFacade.deleteUser(user.getId());
        // Then
        Mockito.verify(userDbService, times(1)).deleteUser(user.getId());
    }
}
