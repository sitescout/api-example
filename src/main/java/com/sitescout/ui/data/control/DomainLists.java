package com.sitescout.ui.data.control;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.DomainListDTO;
import com.sitescout.dsp.api.model.dto.ListDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches domain lists of a particular advertiser.
 */
@Named
@ViewScoped
public class DomainLists extends APIStatsEntity {

    public ListDTO<DomainListDTO> getDetails(int advertiserKey) {
        return (ListDTO<DomainListDTO>) super.getDetailsVarargs("", advertiserKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/domainLists";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<ListDTO<DomainListDTO>>() {
        };
    }
}


