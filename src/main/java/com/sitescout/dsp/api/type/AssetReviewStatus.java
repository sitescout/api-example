package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AssetReviewStatus {
    pending,
    eligible,
    review,
    hold,
    denied,
    blocked;

    @JsonCreator
    public static AssetReviewStatus fromString(String value) {
        if (value != null) {
            return valueOf(value.toLowerCase());
        } else {
            return null;
        }
    }
}
