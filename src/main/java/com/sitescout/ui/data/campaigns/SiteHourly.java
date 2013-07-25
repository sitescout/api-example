package com.sitescout.ui.data.campaigns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.Map;

/**
 * Fetches the hourly details for a particular site.
 *
 * @author sean
 */
@Named
@ViewScoped
public class SiteHourly extends APIStatsEntity {

    public HourlyEntityStatsDTO<SiteRuleDTO> getDetails(String siteRef, int advertiserKey, int campaignKey) {
        return (HourlyEntityStatsDTO<SiteRuleDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/sites/" + siteRef;
    }

    @Override
    protected TypeReference<HourlyEntityStatsDTO<SiteRuleDTO>> getEntityTypeReference() {
        return new TypeReference<HourlyEntityStatsDTO<SiteRuleDTO>>() {
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
