package com.github.flombois.services.notification;

import com.github.flombois.entities.Appointment;
import com.github.flombois.entities.BookingStatus;
import com.github.flombois.entities.Notification;
import com.github.flombois.entities.User;
import com.github.flombois.events.AppointmentUpdate;
import com.github.flombois.repositories.NotificationRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService, ApplicationListener<AppointmentUpdate> {

    private final ApplicationContext applicationContext;

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(ApplicationContext applicationContext, NotificationRepository notificationRepository) {
        this.applicationContext = applicationContext;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(User recipient, String message) {
       return notificationRepository.save(applicationContext.getBean(Notification.class, recipient, message));
    }

    /**
     * When an appointment is updated check if notification have to be sent out
     * @param update The consumed event
     */
    @Override
    public void onApplicationEvent(AppointmentUpdate update) {
        Appointment appointment = update.getAppointment();
        BookingStatus status = appointment.getStatus();
        if (status.sendCustomerNotification(appointment)) {
            createNotification(appointment.getCustomer(), status.customerNotification(appointment));
        }
        if (status.sendOwnerNotification(appointment)) {
            createNotification(appointment.getServiceProvider().getOwner(), status.ownerNotification(appointment));
        }
    }
}
