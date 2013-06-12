package com.sitescout.dsp.api.model.params;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortDirection {
    ASC,
    DESC;

    public SortDirection fromString(String enumStr) {
        if (enumStr != null) {
            enumStr = enumStr.trim().toUpperCase();
        }
        return valueOf(enumStr);
    }

    @JsonValue
    public String getJsonValue() {
        return this.name().toLowerCase();
    }
}
