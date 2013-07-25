package com.sitescout.ui.data.control;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.ListDTO;
import com.sitescout.dsp.api.model.dto.OfferDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches offers of a particular advertiser.
 */
@Named
@ViewScoped
public class Offers extends APIStatsEntity {

    public ListDTO<OfferDTO> getDetails(int advertiserKey) {
        return (ListDTO<OfferDTO>) super.getDetailsVarargs("", advertiserKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/offers";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<ListDTO<OfferDTO>>() {
        };
    }
}


