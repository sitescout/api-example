package com.sitescout.ui.data.graph;

import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.ui.data.campaigns.CampaignHourly;
import com.sitescout.ui.data.campaigns.CreativeHourly;
import com.sitescout.ui.data.campaigns.SiteHourly;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Converts Hourly statistics from API to a graphable data type.
 */
@Named
@ViewScoped
public class CampaignHourlyGraph implements Serializable {
    @Inject
    CreativeHourly creativeHourly;
    @Inject
    SiteHourly siteHourly;
    @Inject
    CampaignHourly campaignHourly;

    HourlyEntityStatsDTO<?> campaignData;
    private CartesianChartModel categoryModel;

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public CartesianChartModel getCategoryModel(String siteRef, int advertiserKey, int campaignKey) {
        campaignData = siteHourly.getDetails(siteRef, advertiserKey, campaignKey);
        createCategoryModel(campaignData);
        if (categoryModel.getSeries().isEmpty()) {
            return null;
        }
        return categoryModel;
    }

    public CartesianChartModel getCategoryModel(int advertiserKey, int campaignKey) {
        campaignData = campaignHourly.getDetails(advertiserKey, campaignKey);
        createCategoryModel(campaignData);
        if (categoryModel.getSeries().isEmpty()) {
            return null;
        }
        return categoryModel;
    }

    public CartesianChartModel getCreativeModel(int advertiserKey, int campaignKey, int creativeID) {
        campaignData = creativeHourly.getDetails("", advertiserKey, campaignKey, creativeID);
        createCategoryModel(campaignData);
        if (categoryModel.getSeries().isEmpty()) {
            return null;
        }
        return categoryModel;
    }

    private void createCategoryModel(HourlyEntityStatsDTO<?> campaignData) {
        categoryModel = new CartesianChartModel();

        ChartSeries bids = new ChartSeries();
        bids.setLabel("Bids");
        for (HourlyStatsDTO hourStats : campaignData.getStatsList()) {
            StatsDTO stats = hourStats.getStats();
            bids.set(hourStats.getHour().toString(), stats.getImpressionsBid() - stats.getImpressionsWon());
        }

        ChartSeries wins = new ChartSeries();
        wins.setLabel("Wins");
        for (HourlyStatsDTO hourStats : campaignData.getStatsList()) {
            StatsDTO stats = hourStats.getStats();
            wins.set(hourStats.getHour().toString(), stats.getImpressionsWon());
        }

        if (!(wins.getData().isEmpty() && bids.getData().isEmpty())) {
        categoryModel.addSeries(wins);
        categoryModel.addSeries(bids);
        }
    }

}