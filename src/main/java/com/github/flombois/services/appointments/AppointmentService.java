package com.github.flombois.services.appointments;


import com.github.flombois.entities.Appointment;
import com.github.flombois.entities.ServiceProvider;
import com.github.flombois.entities.User;
import com.github.flombois.utils.TimeSlot;

public interface AppointmentService {

    /**
     * Check service provider availabilities and create a new appointment.
     * By default, appointment status is {@link com.github.flombois.entities.BookingStatus.REQUESTED}
     * @param serviceProvider An existing service provider
     * @param customer An existing customer
     * @param timeSlot The time slot to check
     * @return An optional {@link Appointment}
     * @throws AppointmentServiceException If the time slot cannot be booked.
     */
    Appointment requestBooking(ServiceProvider serviceProvider, User customer, TimeSlot timeSlot) throws
            AppointmentServiceException;

    /**
     * Approve the specified appointment
     * @param appointment The appointment to update
     */
    void approve(Appointment appointment);

    /**
     * Reject the specified appointment
     * @param appointment The appointment to update
     */
    void reject(Appointment appointment);

    /**
     * Cancel the specified appointment
     * @param appointment The appointment to update
     */
    void cancel(Appointment appointment);

    /**
     * Close the specified appointment
     * @param appointment The appointment to update
     */
    void complete(Appointment appointment);
}
