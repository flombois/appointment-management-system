package com.github.flombois.services.appointments;

import com.github.flombois.entities.Appointment;
import com.github.flombois.entities.BookingStatus;
import com.github.flombois.entities.ServiceProvider;
import com.github.flombois.entities.User;
import com.github.flombois.events.AppointmentUpdate;
import com.github.flombois.repositories.AppointmentRepository;
import com.github.flombois.utils.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final ApplicationContext applicationContext;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ApplicationContext applicationContext,
                                  ApplicationEventPublisher applicationEventPublisher) {
        this.appointmentRepository = appointmentRepository;
        this.applicationContext = applicationContext;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Appointment requestBooking(ServiceProvider serviceProvider, User customer, TimeSlot timeSlot) throws
            AppointmentServiceException {
        // Ensure requested time slot does not overlap an existing appointment
        if(!appointmentRepository.isAvailable(serviceProvider, timeSlot.getStartTime(), timeSlot.getEndTime())) {
            throw new AppointmentServiceException("The requested timeslot cannot be booked");
        }
        Appointment appointment = appointmentRepository.save(applicationContext.getBean(Appointment.class,
                serviceProvider, customer, timeSlot));
        // Publish the appointment creation
        applicationEventPublisher.publishEvent(new AppointmentUpdate(this, appointment));
        return appointment;
    }

    @Override
    public void approve(Appointment appointment) {
        appointment.setStatus(BookingStatus.APPROVED);
        appointmentRepository.save(appointment);
        applicationEventPublisher.publishEvent(new AppointmentUpdate(this, appointment));
    }

    @Override
    public void reject(Appointment appointment) {
        appointment.setStatus(BookingStatus.REJECTED);
        appointmentRepository.save(appointment);
        applicationEventPublisher.publishEvent(new AppointmentUpdate(this, appointment));
    }

    @Override
    public void cancel(Appointment appointment) {
        appointment.setStatus(BookingStatus.CANCELED);
        appointmentRepository.save(appointment);
        applicationEventPublisher.publishEvent(new AppointmentUpdate(this, appointment));
    }

    @Override
    public void complete(Appointment appointment) {
        appointment.setStatus(BookingStatus.COMPLETED);
        appointmentRepository.save(appointment);
        applicationEventPublisher.publishEvent(new AppointmentUpdate(this, appointment));
    }
}
