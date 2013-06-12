package com.sitescout.dsp.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DomainListType {
    whitelist,
    blacklist;

    @JsonCreator
    public static DomainListType fromJson(String valueStr) {
        return valueStr != null ? valueOf(valueStr.toLowerCase()) : null;
    }
}
