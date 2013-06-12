package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.CampaignDetailsSiteDetails;
import com.sitescout.ui.data.CampaignDetailsSiteStats;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


/**
 * To change this template use File | Settings | File Templates.
 */
@Named
@ViewScoped
public class CampaignDetailsSitePage implements Serializable {

    @Inject
    CampaignDetailsSiteStats campaignDetailsSiteStats;
    @Inject
    CampaignDetailsSiteDetails campaignDetailsSiteDetails;
    String siteRef;
    String campaignKey;

    public String getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    public String getCampaignKey() {
        return campaignKey;
    }

    public void setCampaignKey(String campaignKey) {
        this.campaignKey = campaignKey;
    }

    public String findNext(String siteRef) {
        StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> statList = campaignDetailsSiteStats.getStatsList();
        for (EntityStatsDTO<SiteRuleDTO> entityStats : statList.getResults()) {
            if (entityStats.getEntity().getSiteRef().equals(siteRef)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == statList.getResults().size() - 1) {
                    return "";
                }
                return statList.getResults().get(index + 1).getEntity().getSiteRef();
            }
        }
        return "";
    }

    public String findPrev(String siteRef) {
        StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> statList = campaignDetailsSiteStats.getStatsList();
        for (EntityStatsDTO<SiteRuleDTO> entityStats : statList.getResults()) {
            if (entityStats.getEntity().getSiteRef().equals(siteRef)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == 0) {
                    return "";
                }
                return statList.getResults().get(index - 1).getEntity().getSiteRef();
            }
        }
        return "";
    }

    public HourlyEntityStatsDTO<SiteRuleDTO> getSelectedSite(String siteRef, int advertiserKey, String campaignKey) throws IOException {
        return campaignDetailsSiteDetails.getDetails(siteRef, advertiserKey, Integer.parseInt(campaignKey));
    }
}
