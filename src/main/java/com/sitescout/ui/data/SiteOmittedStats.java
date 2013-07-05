package com.sitescout.ui.data;

/**
 Fetches statistics that were not included in the Sites Table
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;

import javax.inject.Named;

@Named
public class SiteOmittedStats extends APIStatsEntity {

    public EntityStatsDTO<SiteRuleDTO> getDetails(String siteRef, int advertiserKey, int campaignKey) {
        return (EntityStatsDTO<SiteRuleDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/sites/" + siteRef;
    }

    @Override
    TypeReference getEntityTypeReference() {
        return new TypeReference<EntityStatsDTO<SiteRuleDTO>>() {
        };
    }

}