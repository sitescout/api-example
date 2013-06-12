package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.CampaignDetailsCreativeDetails;
import com.sitescout.ui.data.CampaignDetailsCreativeStats;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Page dedicated to creatives (hourly).
 */
@Named
@ViewScoped
public class CampaignDetailsCreativePage implements Serializable {
    @Inject
    CampaignDetailsCreativeStats campaignDetailsCreativeStats;
    @Inject
    CampaignDetailsCreativeDetails campaignDetailsCreativeDetails;
    String creativeId;
    String campaignKey;

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getCampaignKey() {
        return campaignKey;
    }

    public void setCampaignKey(String campaignKey) {
        this.campaignKey = campaignKey;
    }


    public String findNext(String creativeId) {
        int creativeIdNum = Integer.parseInt(creativeId);
        StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> statsList = campaignDetailsCreativeStats.getStatsList();
        for (EntityStatsDTO<CreativeDTO> entityStats : statsList.getResults()) {
            if (entityStats.getEntity().getCreativeId().equals(creativeIdNum)) {
                int index = statsList.getResults().indexOf(entityStats);
                if (index == statsList.getResults().size() - 1) {
                    return "";
                }
                return statsList.getResults().get(index + 1).getEntity().getCreativeId().toString();
            }
        }
        return "";
    }

    public String findPrev(String creativeId) {
        int creativeIdNum = Integer.parseInt(creativeId);
        StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> statsList = campaignDetailsCreativeStats.getStatsList();
        for (EntityStatsDTO<CreativeDTO> entityStats : statsList.getResults()) {
            if (entityStats.getEntity().getCreativeId().equals(creativeIdNum)) {
                int index = statsList.getResults().indexOf(entityStats);
                if (index == 0) {
                    return "";
                }
                return statsList.getResults().get(index - 1).getEntity().getCreativeId().toString();
            }
        }
        return "";
    }

    public HourlyEntityStatsDTO<CreativeDTO> getSelectedCreative(String creativeId, int advertiserKey, String campaignKey) throws IOException {
        return campaignDetailsCreativeDetails.getDetails("", advertiserKey, Integer.parseInt(campaignKey), Integer.parseInt(creativeId));
    }
}

