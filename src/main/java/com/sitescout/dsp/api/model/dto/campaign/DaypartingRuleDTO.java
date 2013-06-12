package com.sitescout.dsp.api.model.dto.campaign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.joda.time.LocalTime;

import java.util.Set;

public class DaypartingRuleDTO {
    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

        @JsonValue
        public String toJsonString() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static DayOfWeek fromJsonString(String valueStr) {
            return valueStr == null ? null : valueOf(valueStr.toUpperCase());
        }
    }

    private LocalTime fromTime;
    private LocalTime toTime;
    private Set<DayOfWeek> daysOfWeek;

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaypartingRuleDTO that = (DaypartingRuleDTO) o;

        if (daysOfWeek != null ? !daysOfWeek.equals(that.daysOfWeek) : that.daysOfWeek != null) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (toTime != null ? !toTime.equals(that.toTime) : that.toTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromTime != null ? fromTime.hashCode() : 0;
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        result = 31 * result + (daysOfWeek != null ? daysOfWeek.hashCode() : 0);
        return result;
    }
}
