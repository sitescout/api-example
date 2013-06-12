package com.sitescout.dsp.api.model.dto.campaign;

import com.sitescout.dsp.api.type.UserFrequencyType;

public class FrequencyCappingDTO {
    public static final int MAX_IMPRESSIONS = 99;
    public static final int LONGEST_PERIOD_IN_HOURS = 72;

    private Integer impressions;
    private Integer periodInHours;
    private UserFrequencyType type;

    public Integer getImpressions() {
        return impressions;
    }

    public void setImpressions(Integer impressions) {
        this.impressions = impressions;
    }

    public Integer getPeriodInHours() {
        return periodInHours;
    }

    public void setPeriodInHours(Integer periodInHours) {
        this.periodInHours = periodInHours;
    }

    public UserFrequencyType getType() {
        return type;
    }

    public void setType(UserFrequencyType type) {
        this.type = type;
    }
}
