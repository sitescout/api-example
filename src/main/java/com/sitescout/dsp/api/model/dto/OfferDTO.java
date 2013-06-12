package com.sitescout.dsp.api.model.dto;

public class OfferDTO extends LinkedDTO {
    public enum ClickthruTagType {
        REDIRECT, SCRIPT;
    }

    public static final int MAX_LENGTH_NAME = 255;

    private Integer offerId;
    private String name;
    private Double defaultRevenue;

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDefaultRevenue() {
        return defaultRevenue;
    }

    public void setDefaultRevenue(Double defaultRevenue) {
        this.defaultRevenue = defaultRevenue;
    }
}
