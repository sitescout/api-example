package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * Fetches creative statistics of a particular campaign.
 */
@Named
@ConversationScoped
public class CreativeStats extends APIStatsEntity {

    public StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) {
        return (StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
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


