package com.sitescout.dsp.api.type;

public enum AccessType {

    NO_ACCESS,
    READ_ONLY,
    READ_AND_WRITE;

    public static AccessType fromOrdinal(Integer n) {
        if (n == null || n < 0 || n >= AccessType.values().length) {
            return AccessType.NO_ACCESS;
        } else {
            return AccessType.values()[n];
        }
    }
}
