package com.github.flombois.repositories;

import com.github.flombois.entities.Appointment;
import com.github.flombois.entities.ServiceProvider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface AppointmentRepository extends CrudRepository<Appointment, UUID> {

    @Query(value = """
            select a from Appointment a
            where a.serviceProvider = :serviceProvider
            and a.status = 'APPROVED'
            and (a.startTime between :startTime and :endTime or a.endTime between :startTime and :endTime)""")
    boolean isAvailable(@Param("serviceProvider") ServiceProvider serviceProvider,
                        @Param("startTime") ZonedDateTime startTime, @Param("endTime") ZonedDateTime endTime);
}
