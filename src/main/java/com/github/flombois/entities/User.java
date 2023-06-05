package com.github.flombois.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany
    private List<Appointment> appointments = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public static User of(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
