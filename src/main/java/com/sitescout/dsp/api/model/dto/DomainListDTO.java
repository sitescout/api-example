package com.sitescout.dsp.api.model.dto;


import com.sitescout.dsp.api.type.DomainListType;

import java.util.Set;

public class DomainListDTO extends LinkedDTO {
    public static final int MAX_LENGTH_NAME = 256;

    private Integer domainListId;
    private String name;
    private Set<String> domains;
    private DomainListType type;

    public Integer getDomainListId() {
        return domainListId;
    }

    public void setDomainListId(Integer domainListId) {
        this.domainListId = domainListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getDomains() {
        return domains;
    }

    public void setDomains(Set<String> domains) {
        this.domains = domains;
    }

    public DomainListType getType() {
        return type;
    }

    public void setType(DomainListType type) {
        this.type = type;
    }
}
