package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AssetStatus {
    online,
    offline,
    archived;

    @JsonCreator
    public static AssetStatus fromString(String value) {
        if (value != null) {
            return valueOf(value.toLowerCase());
        } else {
            return null;
        }
    }
}
