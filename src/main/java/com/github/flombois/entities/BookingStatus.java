package com.github.flombois.entities;

import com.github.flombois.events.AppointmentUpdate;
import com.github.flombois.services.notification.NotificationServiceImpl;
import org.apache.logging.log4j.util.Strings;

import java.time.format.DateTimeFormatter;

public enum BookingStatus {


    REQUESTED {
        @Override
        public String customerNotification(Appointment appointment) {
            return String.format("Your appointment request has been submitted to %s.", appointment.getServiceProvider()
                    .getName());
        }

        @Override
        public String ownerNotification(Appointment appointment) {
            return String.format("%s has requested an appointment on the %s.", appointment.getCustomer().getUsername(),
                    appointment.getStartTime().format(DATE_FORMAT));
        }
    }, APPROVED {

        @Override
        public String customerNotification(Appointment appointment) {
            return String.format("Your appointment request has been approved by %s.", appointment.getServiceProvider()
                    .getName());
        }


    }, REJECTED {

        @Override
        public String customerNotification(Appointment appointment) {
            return String.format("Your appointment request has been rejected by %s.", appointment.getServiceProvider()
                    .getName());
        }

    }, CANCELED {

        @Override
        public String customerNotification(Appointment appointment) {
            return String.format("%s has cancelled your appointment on the %s", appointment.getServiceProvider()
                    .getName(), appointment.getStartTime().format(DATE_FORMAT));
        }

    }, COMPLETED;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Customer notification content
     * @see NotificationServiceImpl#onApplicationEvent(AppointmentUpdate)
     * @param appointment The current appointment
     * @return The notification message (empty by default)
     */
    public String customerNotification(Appointment appointment) {
       return Strings.EMPTY;
    }

    /**
     * Check if the booking status requires the customer to be notified about the update
     * @see NotificationServiceImpl#onApplicationEvent(AppointmentUpdate)
     * @param appointment The current appointment
     * @return true if the customer has to be notified, false otherwise
     */
    public boolean sendCustomerNotification(Appointment appointment) {
        return !customerNotification(appointment).isBlank();
    }

    /**
     * Owner notification content
     * @see NotificationServiceImpl#onApplicationEvent(AppointmentUpdate)
     * @param appointment The current appointment
     * @return The notification message (empty by default)
     */
    public String ownerNotification(Appointment appointment) {
       return Strings.EMPTY;
    }

    /**
     * Check if the booking status requires the service provider owner to be notified about the update
     * @see NotificationServiceImpl#onApplicationEvent(AppointmentUpdate)
     * @param appointment The current appointment
     * @return true if the customer has to be notified, false otherwise
     */
    public boolean sendOwnerNotification(Appointment appointment) {
        return !ownerNotification(appointment).isBlank();
    }
}
