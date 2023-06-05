package com.github.flombois.configuration;

import com.github.flombois.entities.Appointment;
import com.github.flombois.entities.ServiceProvider;
import com.github.flombois.entities.User;
import com.github.flombois.utils.TimeSlot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Configuration
public class AppointmentConfig {

    @Bean
    @Scope(value = "prototype")
    public Appointment createAppointment(ServiceProvider serviceProvider, User customer, TimeSlot timeSlot) {
        Objects.requireNonNull(serviceProvider);
        Objects.requireNonNull(customer);
        Objects.requireNonNull(timeSlot);
        Appointment appointment = new Appointment();
        appointment.setServiceProvider(serviceProvider);
        appointment.setCustomer(customer);
        appointment.setStartTime(timeSlot.getStartTime());
        appointment.setEndTime(timeSlot.getEndTime());
        return appointment;
    }
}
