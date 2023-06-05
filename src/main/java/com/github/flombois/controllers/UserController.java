package com.github.flombois.controllers;

import com.github.flombois.entities.User;
import com.github.flombois.services.users.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(getUserService().listAll());
    }

    /**
     * Cannot be mapped directly to /{id} because of ambiguous mapping
     * @param id The looked up UUID
     * @return A spring-made HttpResponse
     */
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.of(getUserService().fetchUser(id));
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        // Check if the path parameter can be treated as UUID
        // Otherwise consider it a username
        if(isUUID(username)) {
            return getUser(UUID.fromString(username));
        } else {
            return ResponseEntity.of(getUserService().fetchUser(username));
        }
    }


    public UserService getUserService() {
        return userService;
    }

    private boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
