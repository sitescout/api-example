package com.sitescout.ui.data.control;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeAssetDTO;
import com.sitescout.dsp.api.model.dto.ListDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches assets of a particular creative.
 */
@Named
@ViewScoped
public class CreativeAssets extends APIStatsEntity {

    public ListDTO<CreativeAssetDTO> getDetails(int advertiserKey) {
        return (ListDTO<CreativeAssetDTO>) super.getDetailsVarargs("", advertiserKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/creatives/assets";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<ListDTO<CreativeAssetDTO>>() {
        };
    }
}


