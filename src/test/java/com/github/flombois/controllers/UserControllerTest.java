package com.github.flombois.controllers;

import com.github.flombois.entities.User;
import com.github.flombois.services.users.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    @DisplayName("Endpoint /users/{id} should respond with the user details matching the provided uuid")
    public void testFetchExistingUserById() throws Exception {
        UUID expectedId = UUID.randomUUID();
        User userSpy = spy(User.of("test"));

        when(userSpy.getId()).thenReturn(expectedId);
        when(userService.fetchUser(eq(expectedId))).thenReturn(Optional.of(userSpy));

        mockMvc.perform(get("/users/{id}", expectedId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                {
                   "id":"%s",
                   "username":"test",
                   "appointments":[]
                }
                """.formatted(expectedId)));
    }

    @Test
    @WithMockUser
    @DisplayName("Endpoint /users/{id} should respond with 404 NOT FOUND if provided uuid doesn't match any " +
            "existing user")
    public void testFetchNonExistingUserById() throws Exception {
        when(userService.fetchUser(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{id}", UUID.randomUUID())).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @WithMockUser
    @DisplayName("Endpoint /users/{username} should respond with the user details matching the provided username")
    public void testFetchExistingUserByUsername() throws Exception {
        UUID expectedId = UUID.randomUUID();
        User userSpy = spy(User.of("test"));

        when(userSpy.getId()).thenReturn(expectedId);
        when(userService.fetchUser(eq("test"))).thenReturn(Optional.of(userSpy));

        mockMvc.perform(get("/users/test")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                {
                   "id":"%s",
                   "username":"test",
                   "appointments":[]
                }
                """.formatted(expectedId)));
    }

    @Test
    @WithMockUser
    @DisplayName("Endpoint /users/{username} should respond with 404 NOT FOUND if provided username doesn't match " +
            "any existing user")
    public void testFetchNonExistingUserByUsername() throws Exception {
        when(userService.fetchUser(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/test")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @WithMockUser
    @DisplayName("Endpoint /users should list all existing registered users in the database")
    public void testFetchAllExistingUsers() throws Exception {
        UUID expectedId = UUID.randomUUID();
        User userSpy = spy(User.of("test"));

        when(userSpy.getId()).thenReturn(expectedId);
        when(userService.listAll()).thenReturn(List.of(userSpy));


        mockMvc.perform(get("/users")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [{
                   "id":"%s",
                   "username":"test",
                   "appointments":[]
                }]
                """.formatted(expectedId)));
    }
}
