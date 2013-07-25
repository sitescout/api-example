package com.sitescout.ui.data.campaigns;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.APIStatsEntity;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.Map;

/**
 * Fetches the campaign statistics for a given advertiser.
 *
 * @author sean
 */
@Named
@ViewScoped
public class CampaignStats extends APIStatsEntity {

    Integer pageNum = 1;
    Integer pageSize = 20;

    public Integer getPageSize() {

        return pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != this.pageSize && pageSize > 0 ) {
            setPageNum(1);
            this.pageSize = pageSize;
        }
    }

    public void nextPage() {
        setPageNum(getPageNum()+1);
    }

    public void prevPage() {
        setPageNum(getPageNum()-1);
    }

    public StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> getDetails(int advertiserKey) {
        if (advertiserKey == 0) {
            return null;
        }
        return (StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO>) super.getDetailsVarargs("", advertiserKey);

    }

    @Override
    protected String getLink(String siteRef, Integer... keys) {
        return "https://api.sitescout.com/advertisers/" + keys[0] + "/campaigns/stats";
    }

    @Override
    protected TypeReference getEntityTypeReference() {
        return new TypeReference<StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO>>() {
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