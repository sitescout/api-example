package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserFrequencyType {
    none,
    campaign,
    site,
    zone;

    public static UserFrequencyType getFromOrdinal(int ordinal) {
        UserFrequencyType[] values = values();
        if (ordinal >= values.length) {
            return null;
        } else {
            return values[ordinal];
        }
    }

    @JsonCreator
    public static UserFrequencyType fromString(String value) {
        return value != null ? valueOf(value.toLowerCase()) : null;
    }
}
