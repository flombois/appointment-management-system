package com.github.flombois.repositories;

import com.github.flombois.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
