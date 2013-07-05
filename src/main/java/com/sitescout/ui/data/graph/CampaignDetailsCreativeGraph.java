package com.sitescout.ui.data.graph;

import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.CreativeStats;
import com.sitescout.ui.data.SiteCreatives;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.inject.Inject;
import javax.inject.Named;

@Named
/**
 * Converts Creative statistics from API to a graphable data type.
 */
public class CampaignDetailsCreativeGraph {

    @Inject
    CreativeStats creativeStats;
    @Inject
    SiteCreatives siteCreatives;
    StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> campaignStatsData;
    private CartesianChartModel statModel;

    public void createStatModel(StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> campaignStatData) {
        statModel = new CartesianChartModel();

        ChartSeries bids = new ChartSeries();
        bids.setLabel("Bids");

        for (EntityStatsDTO<CreativeDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            bids.set(entities.getEntity().getCreativeId().toString(), stats.getImpressionsBid() - stats.getImpressionsWon());
        }

        ChartSeries wins = new ChartSeries();
        wins.setLabel("Wins");
        for (EntityStatsDTO<CreativeDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            wins.set(entities.getEntity().getCreativeId().toString(), stats.getImpressionsWon());
        }
        statModel.addSeries(wins);
        statModel.addSeries(bids);
    }

    public CartesianChartModel getStatsModel(int advertiserKey, int campaignKey) {
        campaignStatsData = creativeStats.getDetails(advertiserKey, campaignKey);
        if (campaignStatsData.getTotalCount() == 0) {
            return null;
        }
        createStatModel(campaignStatsData);
        return statModel;
    }

    public CartesianChartModel getStatsModel(String siteRef, int advertiserKey, int campaignKey) {
        campaignStatsData = siteCreatives.getDetails(siteRef, advertiserKey, campaignKey);
        if (campaignStatsData.getTotalCount() == 0) {
            return null;
        }
        createStatModel(campaignStatsData);
        return statModel;
    }
}
