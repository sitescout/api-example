package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SiteType {
    SITE,
    APP,
    EMAIL;

    @JsonValue
    public String toJsonString() {
        return this.name().toLowerCase();
    }
}
