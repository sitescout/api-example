package com.sitescout.dsp.api.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sitescout.dsp.api.model.Views;
import com.sitescout.dsp.api.model.dto.campaign.AudienceCaptureDTO;
import com.sitescout.dsp.api.model.dto.campaign.BudgetDTO;
import com.sitescout.dsp.api.model.dto.campaign.FrequencyCappingDTO;
import com.sitescout.dsp.api.model.dto.campaign.ViewthruConfigDTO;
import com.sitescout.dsp.api.model.params.DateRange;
import com.sitescout.dsp.api.type.AssetReviewStatus;
import com.sitescout.dsp.api.type.AssetStatus;
import com.sitescout.dsp.api.util.csv.CsvProperties;

@CsvProperties({"campaignId", "name"})
public class CampaignDTO extends LinkedDTO {
    public static final int MAX_LENGTH_NAME = 60;
    public static final int MAX_LENGTH_CLICK_URL = 255;
    public static final double MAX_BID = 100;

    private Integer campaignId;
    private String name;
    private AssetStatus status;
    private Double defaultBid;
    @JsonView(Views.EntityViews.Advertiser.class)
    private AssetReviewStatus reviewStatus;
    @JsonView(Views.EntityViews.Advertiser.class)
    private String clickUrl;
    @JsonView(Views.EntityViews.Advertiser.class)
    private FrequencyCappingDTO frequencyCapping;
    @JsonView(Views.EntityViews.Advertiser.class)
    private BudgetDTO budget;
    @JsonView(Views.EntityViews.Advertiser.class)
    private DateRange flightDates;
    @JsonView(Views.EntityViews.Advertiser.class)
    private AudienceCaptureDTO audienceCapture;
    @JsonView(Views.EntityViews.Advertiser.class)
    private String winNotificationUrl;
    @JsonView(Views.EntityViews.Advertiser.class)
    private ViewthruConfigDTO viewthruConfig;

    public CampaignDTO() {
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public Double getDefaultBid() {
        return defaultBid;
    }

    public void setDefaultBid(Double defaultBid) {
        this.defaultBid = defaultBid;
    }

    public AssetReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(AssetReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public FrequencyCappingDTO getFrequencyCapping() {
        return frequencyCapping;
    }

    public void setFrequencyCapping(FrequencyCappingDTO frequencyCapping) {
        this.frequencyCapping = frequencyCapping;
    }

    public BudgetDTO getBudget() {
        return budget;
    }

    public void setBudget(BudgetDTO budget) {
        this.budget = budget;
    }

    public DateRange getFlightDates() {
        return flightDates;
    }

    public void setFlightDates(DateRange flightDates) {
        this.flightDates = flightDates;
    }

    public AudienceCaptureDTO getAudienceCapture() {
        return audienceCapture;
    }

    public void setAudienceCapture(AudienceCaptureDTO audienceCapture) {
        this.audienceCapture = audienceCapture;
    }

    public String getWinNotificationUrl() {
        return winNotificationUrl;
    }

    public void setWinNotificationUrl(String winNotificationUrl) {
        this.winNotificationUrl = winNotificationUrl;
    }

    public ViewthruConfigDTO getViewthruConfig() {
        return viewthruConfig;
    }

    public void setViewthruConfig(ViewthruConfigDTO viewthruConfig) {
        this.viewthruConfig = viewthruConfig;
    }
}
