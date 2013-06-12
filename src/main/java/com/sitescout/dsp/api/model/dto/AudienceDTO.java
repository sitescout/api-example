package com.sitescout.dsp.api.model.dto;

import com.sitescout.dsp.api.util.csv.CsvProperties;

@CsvProperties({"audienceId", "name"})
public class AudienceDTO extends LinkedDTO {
    public enum TagType {
        SCRIPT, LINK, IMAGE;
    }

    public static final int MAX_LENGTH_NAME = 90;

    private Integer audienceId;
    private String name;
    private Boolean isVisible;

    public Integer getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(Integer audienceId) {
        this.audienceId = audienceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

}
