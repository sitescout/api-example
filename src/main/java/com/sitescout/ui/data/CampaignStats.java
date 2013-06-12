package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.IOException;

/**
 * Fetches the campaign statistics for a given advertiser.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignStats extends APIStatsEntity {

    StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> statsList;

    public StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> getStatsList() {
        return statsList;
    }

    public StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> getDetails(int advertiserKey) throws IOException {
        this.statsList = (StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey);
        return statsList;
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