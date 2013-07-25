package com.sitescout.ui.data.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.AudienceDTO;
import com.sitescout.dsp.api.model.dto.stats.*;
import com.sitescout.ui.data.APIStatsEntity;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 Fetches the balance of a particular advertiser
 */

@Named
@ConversationScoped
public class Balance  extends APIStatsEntity {

    public Double getDetails(int advertiserKey) {
        if (advertiserKey == 0) {
            return null;
        }
        return (Double) super.getDetailsVarargs("", advertiserKey);

    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/balance";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<Double>() {
        };
    }

}
