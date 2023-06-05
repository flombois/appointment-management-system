package com.github.flombois.configuration;

import com.github.flombois.entities.Notification;
import com.github.flombois.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Configuration
public class NotificationConfig {

    @Bean
    @Scope("prototype")
    public Notification createNotification(User recipient, String message) {
        Objects.requireNonNull(recipient);
        Objects.requireNonNull(message);
        return Notification.of(recipient, message);
    }

}
