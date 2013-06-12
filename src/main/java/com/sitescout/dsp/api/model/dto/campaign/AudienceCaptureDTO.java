package com.sitescout.dsp.api.model.dto.campaign;

public class AudienceCaptureDTO {
    private Integer clickAudience;
    private Integer landingPageClickAudience;
    private Integer conversionAudience;

    public Integer getClickAudience() {
        return clickAudience;
    }

    public void setClickAudience(Integer clickAudience) {
        this.clickAudience = clickAudience;
    }

    public Integer getLandingPageClickAudience() {
        return landingPageClickAudience;
    }

    public void setLandingPageClickAudience(Integer landingPageClickAudience) {
        this.landingPageClickAudience = landingPageClickAudience;
    }

    public Integer getConversionAudience() {
        return conversionAudience;
    }

    public void setConversionAudience(Integer conversionAudience) {
        this.conversionAudience = conversionAudience;
    }
}
