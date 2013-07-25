package com.sitescout.ui.data.audience;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.AudienceDTO;
import com.sitescout.dsp.api.model.dto.stats.*;
import com.sitescout.ui.data.APIStatsEntity;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
    Fetches a list of audiences of a particular advertiser
 */

@Named
@ConversationScoped
public class AudienceStats  extends APIStatsEntity {

    public StatsListDTO<AudienceEntityStatsDTO, AudienceStatsDTO> getDetails(int advertiserKey) {
        if (advertiserKey == 0) {
            return null;
        }
        return (StatsListDTO<AudienceEntityStatsDTO, AudienceStatsDTO>) super.getDetailsVarargs("", advertiserKey);

    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/audiences/stats";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<AudienceEntityStatsDTO, AudienceStatsDTO>>() {
        };
    }

}
