package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.page.CampaignsPage;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Fetches site statistics of a particular campaign.
 */
@Named
@ConversationScoped
public class SiteStats extends APIStatsEntity {

    @Inject CampaignsPage campaignsPage;

    public StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) {
        return (StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
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
