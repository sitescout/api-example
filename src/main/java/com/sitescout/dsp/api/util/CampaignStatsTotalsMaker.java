package com.sitescout.dsp.api.util;

import com.sitescout.dsp.api.model.dto.stats.StatsDTO;

public class CampaignStatsTotalsMaker {
    private StatsDTO totals;

    private Integer numberOfItems;

    public CampaignStatsTotalsMaker() {
        this.totals = new StatsDTO();
        numberOfItems = 0;
    }

    public void addStats(StatsDTO stats) {
        // Coeffs
        int divisor = numberOfItems == 0 ? 1 : 2;
        totals.setEffectiveCPM((totals.getEffectiveCPM() + stats.getEffectiveCPM()) / divisor);
        totals.setDataEffectiveCPM((totals.getDataEffectiveCPM() + stats.getDataEffectiveCPM()) / divisor);
        totals.setTotalEffectiveCPM((totals.getTotalEffectiveCPM() + stats.getTotalEffectiveCPM()) / divisor);
        totals.setActualCPM(totals.getActualCPM() + stats.getActualCPM() / divisor);
        totals.setClickthruRate((totals.getClickthruRate() + stats.getClickthruRate()) / divisor);
        totals.setCostPerClick((totals.getCostPerClick() + stats.getCostPerClick()) / divisor);
        totals.setOfferClickthruRate((totals.getOfferClickthruRate() + stats.getOfferClickthruRate()) / divisor);
        totals.setConversionRate((totals.getConversionRate() + stats.getConversionRate()) / divisor);
        totals.setProfitPerClick((totals.getProfitPerClick() + stats.getProfitPerClick()) / divisor);
        totals.setCostPerAcquisition((totals.getCostPerAcquisition() + stats.getCostPerAcquisition()) / divisor);
        totals.setRevenuePerMille((totals.getRevenuePerMille() + stats.getRevenuePerMille()) / divisor);

        // Totals
        totals.setImpressionsBid(totals.getImpressionsBid() + stats.getImpressionsBid());
        totals.setImpressionsWon(totals.getImpressionsWon() + stats.getImpressionsWon());
        totals.setAuctionsSpend(totals.getAuctionsSpend() + stats.getAuctionsSpend());
        totals.setClicks(totals.getClicks() + stats.getClicks());
        totals.setDataSpend(totals.getDataSpend() + stats.getDataSpend());
        totals.setTotalSpend(totals.getTotalSpend() + stats.getTotalSpend());
        totals.setSiteScoutRevenue(totals.getSiteScoutRevenue() + stats.getSiteScoutRevenue());
        totals.setOfferClicks(totals.getOfferClicks() + stats.getOfferClicks());
        totals.setConversions(totals.getConversions() + stats.getConversions());
        totals.setViewthruConversions(totals.getViewthruConversions() + stats.getViewthruConversions());
        totals.setRevenue(totals.getRevenue() + stats.getRevenue());

        numberOfItems++;
    }

    public StatsDTO getTotals() {
        return totals;
    }
}
