package com.crud.diver.service;

import com.crud.diver.domain.User;
import com.crud.diver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldGetListOfAllUsers(){
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
        List<User> userList = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(userList);
        // When
        List<User> resultList = userDbService.getAllUsers();
        // Then
        assertEquals(1, resultList.size());
        assertEquals("Lily", resultList.get(0).getName());
        assertEquals(LocalDate.of(2020,1,1), resultList.get(0).getDateOfBirth());
        assertNotNull(resultList);
    }

    @Test
    void shouldSaveUser(){
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
        when(userRepository.save(user)).thenReturn(user);
        // When
        User userReceived = userDbService.saveUser(user);
        // Then
        assertEquals(1L, userReceived.getId());
        assertEquals("Lily", userReceived.getName());
        assertEquals(LocalDate.of(2020,1,1), userReceived.getDateOfBirth());
    }
}
