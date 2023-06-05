package com.github.flombois.events;

import com.github.flombois.entities.Appointment;
import org.springframework.context.ApplicationEvent;

public class AppointmentUpdate extends ApplicationEvent {

    private final Appointment appointment;

    public AppointmentUpdate(Object source, Appointment appointment) {
        super(source);
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
