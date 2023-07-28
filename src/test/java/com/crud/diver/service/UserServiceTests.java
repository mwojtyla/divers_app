package com.crud.diver.service;

import com.crud.diver.domain.User;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserRepository userRepository;

    private User createUserData() {
        return User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
    }

    @Test
    void shouldGetListOfAllUsers() {
        // Given
        User user = createUserData();
        List<User> userList = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(userList);
        // When
        List<User> resultList = userDbService.getAllUsers();
        // Then
        assertEquals(1, resultList.size());
        assertEquals("Lily", resultList.get(0).getName());
        assertEquals(LocalDate.of(2020, 1, 1), resultList.get(0).getDateOfBirth());
        assertNotNull(resultList);
    }

    @Test
    void shouldGetUserById() throws UserNotFoundException {
        // Given
        User user = createUserData();
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        // When
        User userReceived = userDbService.getUserById(1L);
        // Then
        assertEquals(1L, userReceived.getId());
        assertEquals("Lily", userReceived.getName());
        assertEquals(LocalDate.of(2020, 1, 1), userReceived.getDateOfBirth());
    }

    @Test
    void shouldThrowException() {
        // Given
        Long userId = 1L;
        Optional<User> user = Optional.ofNullable(null);
        when(userRepository.findById(1L)).thenReturn(user);
        // When & Then
        assertThrows(
                UserNotFoundException.class,
                () -> userDbService.getUserById(userId),
                "Expected getUser() to throw, but it didn't"
        );
    }

    @Test
    void shouldSaveUser() {
        // Given
        User user = createUserData();
        when(userRepository.save(user)).thenReturn(user);
        // When
        User userReceived = userDbService.saveUser(user);
        // Then
        assertEquals(1L, userReceived.getId());
        assertEquals("Lily", userReceived.getName());
        assertEquals(LocalDate.of(2020, 1, 1), userReceived.getDateOfBirth());
    }

    @Test
    void shouldDeleteUser() {
        // Given
        Long userId = 1L;
        // When
        userDbService.deleteUser(userId);
        // Then
        Mockito.verify(userRepository, times(1)).deleteById(userId);
    }
}
