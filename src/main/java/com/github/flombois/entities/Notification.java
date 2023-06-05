package com.github.flombois.entities;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(updatable = false)
    private ZonedDateTime timestamp;

    @Column(updatable = false)
    private String message;

    @OneToOne
    private User recipient;

    private boolean read = false;

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    private void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public User getRecipient() {
        return recipient;
    }

    private void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public boolean isRead() {
        return read;
    }

    public void read() {
        this.read = true;
    }

    public static Notification of(User recipient, String message) {
        Notification notification = new Notification();
        notification.setTimestamp(ZonedDateTime.now());
        notification.setRecipient(recipient);
        notification.setMessage(message);
        return notification;
    }

}
