package com.github.flombois.repositories;

import com.github.flombois.entities.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NotificationRepository extends CrudRepository<Notification, UUID> {
}
