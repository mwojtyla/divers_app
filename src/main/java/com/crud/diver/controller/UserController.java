package com.crud.diver.controller;

import com.crud.diver.domain.UserDto;
import com.crud.diver.exception.UserNotFoundException;
import com.crud.diver.facade.UserServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/diver/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceFacade userServiceFacade;

    @GetMapping("/authorised/{login}/{password}")
    public ResponseEntity<Boolean> isUserAuthorised(@PathVariable String login, @PathVariable String password) {
        return ResponseEntity.ok(userServiceFacade.isUserAuthorised(login, password));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceFacade.getUser(userId));
    }

    @GetMapping("getUserId/{login}/{password}")
    public ResponseEntity<Long> getUserIdByLoginAndPassword(@PathVariable String login, @PathVariable String password) {
        return ResponseEntity.ok(userServiceFacade.getUserIdByLoginAndPassword(login, password));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        userServiceFacade.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        userServiceFacade.updateUser(userDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userServiceFacade.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
