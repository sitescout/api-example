package com.sitescout.ui.data.campaigns;

/**
 Fetches statistics that were not included in the campaign table
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.inject.Named;

@Named
public class CampaignOmittedStats extends APIStatsEntity {

    public EntityStatsDTO<CampaignDTO> getDetails(int advertiserKey, int campaignKey) {
        return (EntityStatsDTO<CampaignDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<EntityStatsDTO<CampaignDTO>>() {
        };
    }

}