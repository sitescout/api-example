package com.sitescout.ui.data.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.DeniedAttributesDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * Fetches denied attributes of a particular site reference.
 */
@Named
@ConversationScoped
public class DeniedAttributes extends APIStatsEntity {

    String siteRef;

    public String getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    public DeniedAttributesDTO getDetails(String siteRef) {
        return (DeniedAttributesDTO) super.getDetailsVarargs(siteRef);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/sites/" + siteRef + "/deniedAttributes";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<DeniedAttributesDTO>() {
        };
    }
}
