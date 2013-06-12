package com.sitescout.dsp.api.model.dto.stats;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.sitescout.dsp.api.model.Views;
import com.sitescout.dsp.api.util.csv.CsvProperties;
import com.sitescout.dsp.api.util.csv.CsvType;
import com.sitescout.dsp.api.util.csv.CsvViews;

@CsvProperties({"impressionsBid", "impressionsWon", "effectiveCPM", "actualCPM", "auctionsSpend", "dataEffectiveCPM",
        "dataSpend", "totalEffectiveCPM", "totalSpend", "siteScoutRevenue", "clicks", "clickthruRate", "costPerClick",
        "offerClicks", "offerClickthruRate", "conversions", "conversionRate", "viewthruConversions", "profitPerClick",
        "costPerAcquisition", "revenuePerMille", "revenue"})
@JsonFilter("statsFilter")
public class StatsDTO {
    // General
    @CsvType(CsvType.Value.NUMBER)
    private int impressionsBid;
    @CsvType(CsvType.Value.NUMBER)
    private int impressionsWon;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double effectiveCPM;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double auctionsSpend;

    // Clicks
    @CsvType(CsvType.Value.NUMBER)
    private int clicks;
    @CsvType(CsvType.Value.PERCENT)
    private double clickthruRate;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double costPerClick;

    // Offers
    @CsvType(CsvType.Value.NUMBER)
    private int offerClicks;
    @CsvType(CsvType.Value.PERCENT)
    private double offerClickthruRate;
    @CsvType(CsvType.Value.NUMBER)
    private int conversions;
    @CsvType(CsvType.Value.PERCENT)
    private double conversionRate;
    @CsvType(CsvType.Value.NUMBER)
    private int viewthruConversions;
    @CsvType(CsvType.Value.CURRENCY)
    private double profitPerClick;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double costPerAcquisition;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double revenuePerMille;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double revenue;

    // Totals
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double totalEffectiveCPM;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double totalSpend;

    // Admin
    @CsvViews(CsvViews.Value.ADMIN) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Admin.class)
    private double siteScoutRevenue;
    @CsvViews(CsvViews.Value.ADMIN) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Admin.class)
    private double actualCPM;

    // Data
    @CsvType(CsvType.Value.CURRENCY)
    private double dataEffectiveCPM;
    @CsvViews({CsvViews.Value.ADVERTISER, CsvViews.Value.ADMIN}) @CsvType(CsvType.Value.CURRENCY)
    @JsonView(Views.StatsViews.Advertiser.class)
    private double dataSpend;

    private Integer hour;

    public int getImpressionsBid() {
        return impressionsBid;
    }

    public void setImpressionsBid(int impressionsBid) {
        this.impressionsBid = impressionsBid;
    }

    public int getImpressionsWon() {
        return impressionsWon;
    }

    public void setImpressionsWon(int impressionsWon) {
        this.impressionsWon = impressionsWon;
    }

    public double getEffectiveCPM() {
        return effectiveCPM;
    }

    public void setEffectiveCPM(double effectiveCPM) {
        this.effectiveCPM = effectiveCPM;
    }

    public double getAuctionsSpend() {
        return auctionsSpend;
    }

    public void setAuctionsSpend(double auctionsSpend) {
        this.auctionsSpend = auctionsSpend;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public double getClickthruRate() {
        return clickthruRate;
    }

    public void setClickthruRate(double clickthruRate) {
        this.clickthruRate = clickthruRate;
    }

    public double getCostPerClick() {
        return costPerClick;
    }

    public void setCostPerClick(double costPerClick) {
        this.costPerClick = costPerClick;
    }

    public int getOfferClicks() {
        return offerClicks;
    }

    public void setOfferClicks(int offerClicks) {
        this.offerClicks = offerClicks;
    }

    public double getOfferClickthruRate() {
        return offerClickthruRate;
    }

    public void setOfferClickthruRate(double offerClickthruRate) {
        this.offerClickthruRate = offerClickthruRate;
    }

    public int getConversions() {
        return conversions;
    }

    public void setConversions(int conversions) {
        this.conversions = conversions;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public int getViewthruConversions() {
        return viewthruConversions;
    }

    public void setViewthruConversions(int viewthruConversions) {
        this.viewthruConversions = viewthruConversions;
    }

    public double getProfitPerClick() {
        return profitPerClick;
    }

    public void setProfitPerClick(double profitPerClick) {
        this.profitPerClick = profitPerClick;
    }

    public double getCostPerAcquisition() {
        return costPerAcquisition;
    }

    public void setCostPerAcquisition(double costPerAcquisition) {
        this.costPerAcquisition = costPerAcquisition;
    }

    public double getRevenuePerMille() {
        return revenuePerMille;
    }

    public void setRevenuePerMille(double revenuePerMille) {
        this.revenuePerMille = revenuePerMille;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getTotalEffectiveCPM() {
        return totalEffectiveCPM;
    }

    public void setTotalEffectiveCPM(double totalEffectiveCPM) {
        this.totalEffectiveCPM = totalEffectiveCPM;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public double getSiteScoutRevenue() {
        return siteScoutRevenue;
    }

    public void setSiteScoutRevenue(double siteScoutRevenue) {
        this.siteScoutRevenue = siteScoutRevenue;
    }

    public double getActualCPM() {
        return actualCPM;
    }

    public void setActualCPM(double actualCPM) {
        this.actualCPM = actualCPM;
    }

    public double getDataEffectiveCPM() {
        return dataEffectiveCPM;
    }

    public void setDataEffectiveCPM(double dataEffectiveCPM) {
        this.dataEffectiveCPM = dataEffectiveCPM;
    }

    public double getDataSpend() {
        return dataSpend;
    }

    public void setDataSpend(double dataSpend) {
        this.dataSpend = dataSpend;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
