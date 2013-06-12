package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ExpandingDirection {
    NONE(0),
    UP(13),
    DOWN(14),
    LEFT(15),
    RIGHT(16),
    UP_LEFT(17),
    UP_RIGHT(18),
    DOWN_LEFT(19),
    DOWN_RIGHT(20),
    UP_OR_DOWN(25),
    LEFT_OR_RIGHT(26),
    ANY_DIAGONAL(27);

    private Integer id;

    ExpandingDirection(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static ExpandingDirection fromId(Integer id) {
        for (ExpandingDirection expandingDirection : values()) {
            if (expandingDirection.id.equals(id)) {
                return expandingDirection;
            }
        }
        return null;
    }

    @JsonCreator
    public static ExpandingDirection fromString(String value) {
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
