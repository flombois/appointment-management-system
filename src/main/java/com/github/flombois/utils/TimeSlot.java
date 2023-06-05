package com.github.flombois.utils;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Read only object that encapsulate a time slot
 */
public final class TimeSlot {

    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;

    private TimeSlot(ZonedDateTime startTime, ZonedDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeSlot of(ZonedDateTime startTime, ZonedDateTime endTime) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        if(!endTime.isAfter(startTime)) throw new IllegalArgumentException("End time must be later than start time");
        return new TimeSlot(startTime, endTime);
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }
}
