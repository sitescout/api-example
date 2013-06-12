package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.IOException;

/**
 * Fetches creative statistics of a particular campaign.
 */
@Named
@ConversationScoped
public class CampaignDetailsCreativeStats extends APIStatsEntity {


    StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> statsList;

    public StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> getStatsList() {
        return statsList;
    }

    public StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) throws IOException {
        this.statsList = (StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
        return statsList;
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/creatives";
    }

    @Override
    TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>>() {
        };
    }
}


