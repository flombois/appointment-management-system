package com.github.flombois.configuration;

import com.github.flombois.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

@Configuration
public class UserConfig {

    @Bean
    @Scope(value = "prototype")
    public User createUser(UserDetails userDetails) {
        Objects.requireNonNull(userDetails);
        return User.of(userDetails.getUsername());
    }
}
