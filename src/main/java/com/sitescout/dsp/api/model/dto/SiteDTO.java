package com.sitescout.dsp.api.model.dto;

import com.sitescout.dsp.api.type.SiteType;

public class SiteDTO extends LinkedDTO {
    private String siteRef;
    private ExchangeDTO exchange;
    private SiteType type;
    private String name;
    private String category;

    public String getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    public ExchangeDTO getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeDTO exchange) {
        this.exchange = exchange;
    }

    public SiteType getType() {
        return type;
    }

    public void setType(SiteType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
