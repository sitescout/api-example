package com.sitescout.ui.data;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.OfferDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches offer statistics of a particular campaign.
 */
@Named
@ViewScoped
public class OfferStats extends APIStatsEntity {

    public StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) {
        return (StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/offers";
    }

    @Override
    TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<OfferDTO>, StatsDTO>>() {
        };
    }
}


