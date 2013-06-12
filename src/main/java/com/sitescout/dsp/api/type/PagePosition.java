package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

//NOTE: the enum values cannot be renamed, they are being used as flags in the campaign table field ppjs
public enum PagePosition {
    unknown("unknown"),
    belowTheFold("below_the_fold"),
    aboveTheFold("above_the_fold");

    private String valueStr;

    PagePosition(String valueStr) {
        this.valueStr = valueStr;
    }

    public static Integer getOrdinal(PagePosition pp) {
        if (pp != null) {
            return pp.ordinal();
        }

        return PagePosition.unknown.ordinal();
    }

    public static PagePosition getFromOrdinal(int ordinal) {
        if (ordinal == 1) return belowTheFold;
        if (ordinal == 2) return aboveTheFold;
        return unknown;
    }

    @JsonCreator
    public static PagePosition fromJson(String json) {
        if (json == null) {
            return null;
        }

        for (PagePosition pagePosition : values()) {
            if (pagePosition.valueStr.equals(json.toLowerCase())) {
                return pagePosition;
            }
        }

        return null;
    }

    @JsonValue
    public String toJsonString() {
        return valueStr;
    }
}
