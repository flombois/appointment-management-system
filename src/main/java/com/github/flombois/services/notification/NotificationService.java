package com.github.flombois.services.notification;

import com.github.flombois.entities.Notification;
import com.github.flombois.entities.User;

public interface NotificationService {

    /**
     * Create a new notification to be sent to the specified recipient
     * @param recipient A registered user
     * @param message The notification content
     * @return A persistent instance of {@link Notification}
     */
    Notification createNotification(User recipient, String message);
}
