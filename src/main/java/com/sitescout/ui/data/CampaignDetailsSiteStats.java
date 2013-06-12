package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.IOException;

/**
 * Fetches site statistics of a particular campaign.
 */
@Named
@ConversationScoped
public class CampaignDetailsSiteStats extends APIStatsEntity {

    StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> statsList;

    public StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> getStatsList() {
        return statsList;
    }

    public StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) throws IOException {
        this.statsList = (StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
        return statsList;
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/sites";
    }

    @Override
    TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>>() {
        };
    }
}
