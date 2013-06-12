package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Author: Greg Dunn
 * Date: 12-06-07
 */

public enum RichMediaVendor {
    OTHER_VENDOR,
    DOUBLE_CLICK,
    EYE_WONDER,
    MEDIA_MIND,
    POINT_ROLL,
    UNICAST,
    FLASHTALKING,
    VIDEO_EGG,
    EYE_RETURN,
    SAY_MEDIA,
    PICTELA,
    SPONGECELL,
    ADRIME,
    OGGIFINOGI;

    @JsonCreator
    public static RichMediaVendor fromString(String value) {
        if (value != null) {
            return valueOf(value.toUpperCase());
        } else {
            return null;
        }
    }

    @JsonValue
    public String toJsonString() {
        return this.name().toLowerCase();
    }
}
