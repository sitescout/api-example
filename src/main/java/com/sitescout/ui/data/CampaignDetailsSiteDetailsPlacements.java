package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.IOException;

/**
 * Fetches the campaign details for a particular campaign.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignDetailsSiteDetailsPlacements extends APIStatsEntity {

    public StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> getDetails(String siteRef, int advertiserKey, int campaignKey) throws IOException {
        return (StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/sites/" + siteRef + "/placements";
    }

    @Override
    TypeReference<StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>> getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>>() {
        };
    }
}