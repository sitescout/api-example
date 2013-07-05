package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches the campaign statistics for a given advertiser.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignStats extends APIStatsEntity {


    public StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> getDetails(int advertiserKey) {
        if (advertiserKey == 0) {
            return null;
        }
        return (StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey);

    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/stats";
    }

    @Override
    TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO>>() {
        };
    }

}