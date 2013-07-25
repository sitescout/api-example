package com.sitescout.ui.data.graph;

import com.sitescout.dsp.api.model.dto.OfferDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.campaigns.OfferStats;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Converts Offer statistics from API to a graphable data type.
 */
@Named
public class CampaignDetailsOfferGraph {

    @Inject
    OfferStats offerStats;
    StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO> campaignStatsData;
    private CartesianChartModel statModel;

    public void createStatModel(StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO> campaignStatData) {
        statModel = new CartesianChartModel();

        ChartSeries bids = new ChartSeries();
        bids.setLabel("Bids");

        for (EntityStatsDTO<OfferDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            bids.set(entities.getEntity().getOfferId().toString(), stats.getImpressionsBid() - stats.getImpressionsWon());
        }

        ChartSeries wins = new ChartSeries();
        wins.setLabel("Wins");
        for (EntityStatsDTO<OfferDTO> entities : campaignStatData.getResults()) {
            StatsDTO stats = entities.getStats();
            wins.set(entities.getEntity().getOfferId().toString(), stats.getImpressionsWon());
        }
        statModel.addSeries(wins);
        statModel.addSeries(bids);
    }

    public CartesianChartModel getStatsModel(int advertiserKey, int campaignKey) {
        campaignStatsData = offerStats.getDetails(advertiserKey, campaignKey);
        if (campaignStatsData.getTotalCount() == 0) {
            return null;
        }
        createStatModel(campaignStatsData);
        return statModel;
    }
}
