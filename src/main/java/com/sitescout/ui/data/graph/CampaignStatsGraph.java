package com.sitescout.ui.data.graph;

import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.CampaignStats;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Converts campaign statistics from API to a graphable data type.
 */
@Named
public class CampaignStatsGraph {
    @Inject
    CampaignStats campaignStats;
    StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> campaignStatsData;
    private CartesianChartModel statModel;

    public void createStatModel(StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> campaignStatData) {
        statModel = new CartesianChartModel();

        ChartSeries bids = new ChartSeries();
        bids.setLabel("Bids");

        for (EntityStatsDTO<CampaignDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            bids.set(entities.getEntity().getName(), stats.getImpressionsBid() - stats.getImpressionsWon());
        }

        ChartSeries wins = new ChartSeries();
        wins.setLabel("Wins");
        for (EntityStatsDTO<CampaignDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            wins.set(entities.getEntity().getName(), stats.getImpressionsWon());
        }
        statModel.addSeries(wins);
        statModel.addSeries(bids);
    }

    public CartesianChartModel getStatsModel(int advertiserKey) {
        if (advertiserKey == 0) {
            return null;
        }
        campaignStatsData = campaignStats.getDetails(advertiserKey);

        createStatModel(campaignStatsData);
        return statModel;
    }

}
