package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.AdvertiserKeyProducer;
import com.sitescout.ui.api.APIConnection;
import com.sitescout.ui.data.CampaignHourly;
import com.sitescout.ui.data.CampaignStats;
import com.sitescout.ui.qualifiers.Campaign;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * The main page, allowing selection of campaigns.
 * Based on the selection, it will allow further navigation options: (Hourly, Sites, Creatives, Offers)
 * @author sean
 */
@Named
@ConversationScoped
public class CampaignsPage implements Serializable {

    @Inject private Conversation conversation;
    @Inject CampaignStats campaignStats;
    @Inject CampaignHourly campaignHourly;
    @Inject APIConnection apiConnection;
    @Inject AdvertiserKeyProducer advertiserKeyProducer;
    @Inject Event<CampaignKeyChangeEvent> campaignKeyChangeEvent;

    Collection<Object> selectedRows;
    Integer campaignKey = 0;

    public void advertiserKeyObserver(@Observes AdvertiserKeyProducer.AdvertiserKeyChangeEvent event) {
        setCampaignKey(null);
        setSelectedRows(null);
    }

    public void validateSelectedCampaign(@Observes CalendarBean.DateChangeEvent event) {
        updateSelectedRow();
    }


    @Named @Produces @Campaign @Key
    public Integer getCampaignKey() {
        return campaignKey;
    }

    public void setCampaignKey(Integer campaignKey) {
        this.campaignKey = campaignKey;
        updateSelectedRow();
        campaignKeyChangeEvent.fire(new CampaignKeyChangeEvent());
    }

    public Collection<Object> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(Collection<Object> selectedCampaignKeys) {
        this.selectedRows = selectedCampaignKeys;
    }

    public Integer findNext(int campaignKey) {
        StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> statList =
                campaignStats.getDetails(advertiserKeyProducer.getAdvertiserKey());
        for (EntityStatsDTO<CampaignDTO> entityStats : statList.getResults()) {
            if (entityStats.getEntity().getCampaignId().equals(campaignKey)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == statList.getResults().size() - 1) {
                    return null;
                }
                return statList.getResults().get(index + 1).getEntity().getCampaignId();
            }
        }
        return null;
    }

    public Integer findPrev(int campaignKey) {
        StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> statList =
                campaignStats.getDetails(advertiserKeyProducer.getAdvertiserKey());
        for (EntityStatsDTO<CampaignDTO> entityStats : statList.getResults()) {
            if (entityStats.getEntity().getCampaignId().equals(campaignKey)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == 0) {
                    return null;
                }
                return statList.getResults().get(index - 1).getEntity().getCampaignId();
            }
        }
        return null;
    }


    public String startConvo() {
        if (conversation.isTransient()) {
            conversation.begin();
            return apiConnection.authorize();
        }
        return apiConnection.authorize();
    }

    /**
     * Update which campaign is selected, based on the currently selected row key.
     */
    public void updateSelectedCampaign() {
        if (selectedRows.isEmpty()) {
            this.campaignKey = 0;
        } else {
            int rowPosition = (Integer) selectedRows.iterator().next();
            this.campaignKey = campaignStats.getDetails(advertiserKeyProducer.getAdvertiserKey())
                    .getResults().get(rowPosition).getEntity().getCampaignId();
        }
    }

    /**
     * Ensure that the currently selected campaign is still visible.
     * Set to none if that is not the case.
     */
    public void updateSelectedRow() {
        if (this.selectedRows != null) {
            this.selectedRows.clear();
             List<EntityStatsDTO<CampaignDTO>> campaigns =
                     campaignStats.getDetails(advertiserKeyProducer.getAdvertiserKey()).getResults();
            for (int i = 0; i < campaigns.size(); i++) {
                EntityStatsDTO<CampaignDTO> campaign = campaigns.get(i);
                if (campaign.getEntity().getCampaignId().equals(getCampaignKey())) {
                    this.selectedRows.add(i);
                }
            }
        }
    }


    public HourlyEntityStatsDTO<CampaignDTO> getHourlyDetail(int advertiserKey, int campaignKey) {
        return campaignHourly.getDetails(advertiserKey, campaignKey);
    }

    public class CampaignKeyChangeEvent {

    }
}


