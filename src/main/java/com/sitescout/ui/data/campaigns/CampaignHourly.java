package com.sitescout.ui.data.campaigns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.Map;

/**
 * Fetches the hourly for a particular campaign.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignHourly extends APIStatsEntity {

    public HourlyEntityStatsDTO<CampaignDTO> getDetails(int advertiserKey, int campaignKey) {
        return (HourlyEntityStatsDTO<CampaignDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats";
    }

    @Override
    protected TypeReference<HourlyEntityStatsDTO<CampaignDTO>> getEntityTypeReference() {
        return new TypeReference<HourlyEntityStatsDTO<CampaignDTO>>() {
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


