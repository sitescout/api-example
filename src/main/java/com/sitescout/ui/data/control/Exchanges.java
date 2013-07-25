package com.sitescout.ui.data.control;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.ExchangeDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Fetches exchanges.
 */
@Named
@ViewScoped
public class Exchanges extends APIStatsEntity {

    public List<ExchangeDTO> getDetails() {
        return (List<ExchangeDTO>) super.getDetailsVarargs("");
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/exchanges";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<List<ExchangeDTO>>() {
        };
    }
}


