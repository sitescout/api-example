package com.sitescout.ui.data.graph;

import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.SitePlacements;
import com.sitescout.ui.data.SiteStats;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Converts Site statistics from API to a graphable data type.
 */
@Named
public class CampaignDetailsSiteGraph {
    @Inject
    SitePlacements sitePlacements;
    @Inject
    SiteStats siteStats;
    StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> campaignStatsData;
    private CartesianChartModel statModel;

    public void createStatModel(StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> campaignStatData) {
        statModel = new CartesianChartModel();

        ChartSeries bids = new ChartSeries();
        bids.setLabel("Bids");

        for (EntityStatsDTO<SiteRuleDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            bids.set(entities.getEntity().getDomain(), stats.getImpressionsBid() - stats.getImpressionsWon());
        }

        ChartSeries wins = new ChartSeries();
        wins.setLabel("Wins");
        for (EntityStatsDTO<SiteRuleDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            wins.set(entities.getEntity().getDomain(), stats.getImpressionsWon());
        }
        statModel.addSeries(wins);
        statModel.addSeries(bids);
    }

    public CartesianChartModel getStatsModel(int advertiserKey, int campaignKey) {
        campaignStatsData = siteStats.getDetails(advertiserKey, campaignKey);
        if (campaignStatsData.getTotalCount() == 0) {
            return null;
        }
        createStatModel(campaignStatsData);

        return statModel;
    }

    public CartesianChartModel getStatsModel(String siteRef, int advertiserKey, int campaignKey) {
        campaignStatsData = sitePlacements.getDetails(siteRef, advertiserKey, campaignKey);
        createStatModel(campaignStatsData);
        return statModel;
    }
}
