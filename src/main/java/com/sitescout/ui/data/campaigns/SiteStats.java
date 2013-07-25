package com.sitescout.ui.data.campaigns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Fetches site statistics of a particular campaign.
 */
@Named
@ConversationScoped
public class SiteStats extends APIStatsEntity {

    Integer pageNum = 1;
    Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void nextPage() {
        setPageNum(getPageNum()+1);
    }

    public void prevPage() {
        setPageNum(getPageNum()-1);
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != this.pageSize && pageSize > 0 ) {
            setPageNum(1);
            this.pageSize = pageSize;
        }
    }

    public StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> getDetails(int advertiserKey, int campaignKey) {
        return (StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey, campaignKey);
    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/" + keys[1] + "/stats" + "/sites";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO>>() {
        };
    }

    @Override
    protected Map<String, String> getQueryParams() {
        Map<String, String> querymap;
        querymap = super.getQueryParams();
        querymap.put("page", getPageNum().toString());
        querymap.put("pageSize", getPageSize().toString());
        return querymap;
    }
}
