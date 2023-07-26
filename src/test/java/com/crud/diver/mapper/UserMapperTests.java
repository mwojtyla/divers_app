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

    @Test
    void shouldReturnUserWhenMapUserDtoToUser() {
        // Given
        UserDto userDto = new UserDto(1L, "Lily", "Evans", LocalDate.of(2020,1,1) ,"London", "lily@gmail.com","lilyevans", "lily123");
        // When
        User userReceived = userMapper.mapToUser(userDto);
        // Then
        assertEquals(1L, userReceived.getId());
        assertEquals("Lily", userReceived.getName());
        assertEquals(LocalDate.of(2020,1,1), userReceived.getDateOfBirth());
    }

    @Test
    void shouldReturnUserDtoWhenMapUserToUserDto(){
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
        // When
        UserDto userDtoReceived = userMapper.mapToUserDto(user);
        // Then
        assertEquals(1L, userDtoReceived.getId());
        assertEquals("Lily", userDtoReceived.getName());
        assertEquals(LocalDate.of(2020,1,1), userDtoReceived.getDateOfBirth());
    }
}
