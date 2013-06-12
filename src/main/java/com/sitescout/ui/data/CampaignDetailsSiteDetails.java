package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

/**
 * Fetches the campaign details for a particular campaign.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignDetailsSiteDetails extends APIStatsEntity {

    public HourlyEntityStatsDTO<SiteRuleDTO> getDetails(String siteRef, int advertiserKey, int campaignKey) throws IOException {
        return (HourlyEntityStatsDTO<SiteRuleDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/sites/" + siteRef;
    }

    @Override
    TypeReference<HourlyEntityStatsDTO<SiteRuleDTO>> getEntityTypeReference() {
        return new TypeReference<HourlyEntityStatsDTO<SiteRuleDTO>>() {
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
