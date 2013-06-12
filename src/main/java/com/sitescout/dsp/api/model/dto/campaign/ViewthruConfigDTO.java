package com.sitescout.dsp.api.model.dto.campaign;

import java.util.Set;

public class ViewthruConfigDTO {
    public static final int LONGEST_CONVERSION_WINDOW_IN_DAYS = 90;

    private Integer conversionWindowInDays;
    private Set<Integer> offers;
    private Boolean deduplicationEnabled;

    public Integer getConversionWindowInDays() {
        return conversionWindowInDays;
    }

    public void setConversionWindowInDays(Integer conversionWindowInDays) {
        this.conversionWindowInDays = conversionWindowInDays;
    }

    public Set<Integer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Integer> offers) {
        this.offers = offers;
    }

    public Boolean getDeduplicationEnabled() {
        return deduplicationEnabled;
    }

    public void setDeduplicationEnabled(Boolean deduplicationEnabled) {
        this.deduplicationEnabled = deduplicationEnabled;
    }
}