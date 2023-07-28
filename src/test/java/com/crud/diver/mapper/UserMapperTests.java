package com.crud.diver.mapper;

import com.crud.diver.domain.User;
import com.crud.diver.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

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

    private UserDto createUserDtoData() {
        return UserDto.builder()
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
    void shouldReturnUserWhenMapUserDtoToUser() {
        // Given
        UserDto userDto = createUserDtoData();
        // When
        User userReceived = userMapper.mapToUser(userDto);
        // Then
        assertEquals(1L, userReceived.getId());
        assertEquals("Lily", userReceived.getName());
        assertEquals(LocalDate.of(2020, 1, 1), userReceived.getDateOfBirth());
    }

    @Test
    void shouldReturnUserDtoWhenMapUserToUserDto() {
        // Given
        User user = createUserData();
        // When
        UserDto userDtoReceived = userMapper.mapToUserDto(user);
        // Then
        assertEquals(1L, userDtoReceived.getId());
        assertEquals("Lily", userDtoReceived.getName());
        assertEquals(LocalDate.of(2020, 1, 1), userDtoReceived.getDateOfBirth());
    }
}
