package com.crud.diver.controller;

import com.crud.diver.config.LocalDateAdapter;
import com.crud.diver.domain.User;
import com.crud.diver.domain.UserDto;
import com.crud.diver.facade.UserServiceFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceFacade userServiceFacade;

    private UserDto createUserDto(){
        return new UserDto(1L, "Lily", "Evans", LocalDate.of(2020, 1, 1), "London", "lily@gmail.com", "lilyevans", "lily123");
    }

    @Test
    void shouldReturnTrueWhenUserAuthorised() throws Exception {
        // Given
        String login = "lilyevans";
        String password = "lily123";

        when(userServiceFacade.isUserAuthorised(login, password)).thenReturn(true);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/user/authorised/lilyevans/lily123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnFalseWhenLoginIsNotAvailable() throws Exception {
        // Given
        String login = "lilyevans";

        when(userServiceFacade.isLoginAvailable(login)).thenReturn(false);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/user/loginVerification/lilyevans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldGetUserByUserId() throws Exception {
        // Given
        UserDto userDto = createUserDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);

        when(userServiceFacade.getUser(1L)).thenReturn(userDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Lily")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Evans")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth", Matchers.is(String.valueOf(LocalDate.of(2020, 1, 1)))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localization", Matchers.is("London")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("lily@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is("lilyevans")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("lily123")));
    }

    @Test
    void shouldGetUserIdByLoginAndPassword() throws Exception {
        // Given
        UserDto userDto = createUserDto();

        when(userServiceFacade.getUserIdByLoginAndPassword(userDto.getLogin(), userDto.getPassword())).thenReturn(userDto.getId());
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/diver/user/getUserId/lilyevans/lily123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void shouldCreateUser() throws Exception {
        // Given
        UserDto userDto = createUserDto();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/diver/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(userServiceFacade, times(1)).createUser(userDto);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        // Given
        UserDto userDto = createUserDto();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(userDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/diver/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(userServiceFacade, times(1)).updateUser(userDto);
    }


    @Test
    void shouldDeleteUser() throws Exception {
        // Given
        User user = User.builder()
                .id(1L)
                .name("Lily")
                .surname("Evans")
                .dateOfBirth(LocalDate.of(2020, 1, 1))
                .localization("London")
                .email("lily@gmail.com")
                .login("lilyevans")
                .password("lily123")
                .build();
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/diver/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        Mockito.verify(userServiceFacade, times(1)).deleteUser(user.getId());
    }
}

