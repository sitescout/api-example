package com.sitescout.ui.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 * Fetches the creative details for a particular site.
 *
 * @author sean
 */
@Named
@ViewScoped
public class SiteCreatives extends APIStatsEntity {

    public StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> getDetails(String siteRef, int advertiserKey, int campaignKey) {
        return (StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>) super.getDetailsVarargs(siteRef, advertiserKey, campaignKey);
    }

    @Override
    String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats/sites/" + siteRef + "/creatives";
    }

    @Override
    TypeReference<StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>> getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO>>() {
        };
    }
}