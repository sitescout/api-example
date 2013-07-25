package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.CreativeDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.AdvertiserKeyProducer;
import com.sitescout.ui.data.campaigns.CreativeHourly;
import com.sitescout.ui.data.campaigns.CreativeStats;
import com.sitescout.ui.qualifiers.CreativeId;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The main page, allowing selection of Creatives.
 * Based on the selection, it will allow further navigation options: (Hourly)
 */
@Named
@ConversationScoped
public class CreativesPage implements Serializable {

    @Inject CreativeStats creativeStats;
    @Inject CreativeHourly creativeHourly;
    @Inject AdvertiserKeyProducer advertiserKeyProducer;
    @Inject CampaignsPage campaignsPage;

    Integer creativeId = 0;
    Collection<Object> selectedRows;

    public String next() {
        if (advertiserKeyProducer.getAdvertiserKey() != null) {
            if (creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey())
                    .getLink("next") != null) {
                return "Next Page";
            }
        }
        return "";
    }

    public String prev() {
        if (advertiserKeyProducer.getAdvertiserKey() != null) {
            if (creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey())
                    .getLink("prev") != null) {
                return "Previous Page";
            }
        }
        return "";
    }

    public void campaignKeyObserver(@Observes CampaignsPage.CampaignKeyChangeEvent event) {
        setNull();
    }

    public void advertiserKeyObserver(@Observes AdvertiserKeyProducer.AdvertiserKeyChangeEvent event) {
        setNull();
    }

    public void validateSelectedCampaign(@Observes CalendarBean.DateChangeEvent event) {
        updateSelectedRow();
    }

    @Named @Produces @CreativeId @Key
    public Integer getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Integer creativeId) {
        this.creativeId = creativeId;
        if (creativeId != null) {
            updateSelectedRow();
        }

    }

    public Collection<Object> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(Collection<Object> selectedRows) {
        this.selectedRows = selectedRows;
    }

    public boolean rightPanelLoad() {
        if (selectedRows == null) {
            return false;
        }
        return !selectedRows.isEmpty();
    }

    public Integer findNext(int creativeId) {
        StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> statsList =
                creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey());
        for (EntityStatsDTO<CreativeDTO> entityStats : statsList.getResults()) {
            if (entityStats.getEntity().getCreativeId().equals(creativeId)) {
                int index = statsList.getResults().indexOf(entityStats);
                if (index == statsList.getResults().size() - 1) {
                    return null;
                }
                return statsList.getResults().get(index + 1).getEntity().getCreativeId();
            }
        }
        return null;
    }

    public Integer findPrev(int creativeId) {
        StatsListDTO<EntityStatsDTO<CreativeDTO>, StatsDTO> statsList =
                creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey());
        for (EntityStatsDTO<CreativeDTO> entityStats : statsList.getResults()) {
            if (entityStats.getEntity().getCreativeId().equals(creativeId)) {
                int index = statsList.getResults().indexOf(entityStats);
                if (index == 0) {
                    return null;
                }
                return statsList.getResults().get(index - 1).getEntity().getCreativeId();
            }
        }
        return null;
    }

    public void setNull() {
        setSelectedRows(null);
        setCreativeId(null);
    }

    /**
     * Update which creative is selected, based on the currently selected row key.
     */
    public void updateSelectedCreative() {
        if (selectedRows.isEmpty()) {
            this.creativeId = 0;
        } else {
            int rowPosition = (Integer) selectedRows.iterator().next();
            this.creativeId = creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey())
                    .getResults().get(rowPosition).getEntity().getCreativeId();
        }
    }

    /**
     * Ensure that the currently selected campaign is still visible.
     * Set to none if that is not the case.
     */
    public void updateSelectedRow() {
        if (this.selectedRows != null) {
            this.selectedRows.clear();
            List<EntityStatsDTO<CreativeDTO>> creatives =
                    creativeStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey()).getResults();
            for (int i = 0; i < creatives.size(); i++) {
                EntityStatsDTO<CreativeDTO> creative = creatives.get(i);
                if (creative.getEntity().getCreativeId().equals(getCreativeId())) {
                    this.selectedRows.add(i);
                }
            }
        }
    }

    public HourlyEntityStatsDTO<CreativeDTO> getSelectedCreative(int advertiserKey, int campaignKey, int creativeId) throws IOException {
        return creativeHourly.getDetails("", advertiserKey, campaignKey, creativeId);
    }
}

