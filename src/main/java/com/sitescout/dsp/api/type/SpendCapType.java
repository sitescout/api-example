package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SpendCapType {
    none("none"), daily("daily"), allTime("all_time");

    private String valueStr;

    SpendCapType(String valueStr) {
        this.valueStr = valueStr;
    }

    @JsonCreator
    public static SpendCapType fromString(String value) {
        if (value == null) {
            return null;
        }

        for (SpendCapType spendCapType : values()) {
            if (spendCapType.valueStr.equals(value.toLowerCase())) {
                return spendCapType;
            }
        }

        return null;
    }

    @JsonValue
    public String toJsonString() {
        return valueStr;
    }
}
