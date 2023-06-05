package com.github.flombois.services.users;

import com.github.flombois.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User management
 */
public interface UserService {

    /**
     * Register a new user, has no effect if the user has been already registered.
     * @param username A unique string identifying the user
     */
    void registerUser(String username);

    /**
     * Fetch an existing user
     * @param username A unique string identifying the user
     * @return An optional instance of of {@link User}
     */
    Optional<User> fetchUser(String username);

    /**
     * Fetch an existing user
     * @param id The user UUID
     * @return An optional instance of of {@link User}
     */
    Optional<User> fetchUser(UUID id);

    /**
     * List all the users registered in the system
     * In a real-world scenario make sure to use pagination to avoid fetching large amount of data
     * @return A list of {@link User}
     */
    List<User> listAll();


}
