package com.sitescout.ui.data.campaigns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.Map;

/**
 * Fetches the hourly details for a particular creativeId.
 */
@Named
@ViewScoped
public class CreativeHourly extends APIStatsEntity {

    public HourlyEntityStatsDTO<CreativeDTO> getDetails(String siteRef, int advertiserKey, int campaignKey, int creativeId) {
        return (HourlyEntityStatsDTO<CreativeDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey, creativeId);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/creatives/" + keys[2];
    }

    @Override
    protected TypeReference<HourlyEntityStatsDTO<CreativeDTO>> getEntityTypeReference() {
        return new TypeReference<HourlyEntityStatsDTO<CreativeDTO>>() {
        };
    }

    @Override
    protected Map<String, String> getQueryParams() {
        Map<String, String> querymap;
        querymap = super.getQueryParams();
        querymap.put("hourly", "true");
        return querymap;
    }
}


