package com.sitescout.dsp.api.model.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sitescout.dsp.api.type.AdFormat;

import java.util.EnumSet;
import java.util.Set;

public enum CreativeType {
    BANNER(AdFormat.image, AdFormat.flash),
    TAG(AdFormat.html),
    EXPANDABLE(AdFormat.expandable),
    AD_BUILDER(AdFormat.html);

    private Set<AdFormat> adFormats;

    private CreativeType(AdFormat... adFormats) {
        this.adFormats = EnumSet.of(adFormats[0], adFormats);
    }

    @JsonValue
    public String toJsonString() {
        return this.name().toLowerCase();
    }

}
