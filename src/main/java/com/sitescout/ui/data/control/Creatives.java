package com.sitescout.ui.data.control;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.ListDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Fetches creative statistics of a particular advertiser.
 */
@Named
@ViewScoped
public class Creatives extends APIStatsEntity {

    public ListDTO<CreativeDTO> getDetails(int advertiserKey) {
        return (ListDTO<CreativeDTO>) super.getDetailsVarargs("", advertiserKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/creatives";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<ListDTO<CreativeDTO>>() {
        };
    }
}


