package com.sitescout.ui.data.campaigns;

/**
 Fetches statistics that were not included in the creatives Table
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.inject.Named;

@Named
public class CreativeOmittedStats extends APIStatsEntity {

    public EntityStatsDTO<CreativeDTO> getDetails(String siteRef, int advertiserKey, int campaignKey, int creativeId) {
        return (EntityStatsDTO<CreativeDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey, creativeId);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/creatives/" + keys[2];
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<EntityStatsDTO<CreativeDTO>>() {
        };
    }

}