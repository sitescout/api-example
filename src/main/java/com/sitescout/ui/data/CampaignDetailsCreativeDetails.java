package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

/**
 * Fetches the creative details for a particular creativeId.
 */
@Named
@ViewScoped
public class CampaignDetailsCreativeDetails extends APIStatsEntity {

    public HourlyEntityStatsDTO<CreativeDTO> getDetails(String siteRef, int advertiserKey, int campaignKey, int creativeId) throws IOException {
        return (HourlyEntityStatsDTO<CreativeDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey, creativeId);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/creatives/" + keys[2];
    }

    @Override
    TypeReference<HourlyEntityStatsDTO<CreativeDTO>> getEntityTypeReference() {
        return new TypeReference<HourlyEntityStatsDTO<CreativeDTO>>() {
        };
    }

    @Override
    Map<String, String> getQueryParams() {
        Map<String, String> querymap;
        querymap = super.getQueryParams();
        querymap.put("hourly", "true");
        return querymap;
    }
}


