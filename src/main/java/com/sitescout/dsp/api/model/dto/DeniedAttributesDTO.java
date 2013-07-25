package com.sitescout.dsp.api.model.dto;

import java.util.Set;

public class DeniedAttributesDTO {
    private Set<String> verticals;
    private Set<String> creativeTypes;

    public DeniedAttributesDTO(){

    }

    public DeniedAttributesDTO(Set<String> verticals, Set<String> creativeTypes) {
        this.verticals = verticals;
        this.creativeTypes = creativeTypes;
    }

    public Set<String> getVerticals() {
        return verticals;
    }

    public void setVerticals(Set<String> verticals) {
        this.verticals = verticals;
    }

    public Set<String> getCreativeTypes() {
        return creativeTypes;
    }

    public void setCreativeTypes(Set<String> creativeTypes) {
        this.creativeTypes = creativeTypes;
    }
}
