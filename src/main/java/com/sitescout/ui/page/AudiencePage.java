package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.stats.*;
import com.sitescout.ui.AdvertiserKeyProducer;
import com.sitescout.ui.api.APIConnection;
import com.sitescout.ui.data.audience.AudienceStats;
import com.sitescout.ui.qualifiers.Audience;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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
public class AudiencePage implements Serializable {

    @Inject private Conversation conversation;
    @Inject AudienceStats audienceStats;
    @Inject APIConnection apiConnection;
    @Inject AdvertiserKeyProducer advertiserKeyProducer;

    Collection<Object> selectedRows;
    Integer audienceId = 0;

    public void advertiserKeyObserver(@Observes AdvertiserKeyProducer.AdvertiserKeyChangeEvent event) {
        setaudienceId(null);
        setSelectedRows(null);
    }

    public void valKeyateSelectedAudience(@Observes CalendarBean.DateChangeEvent event) {
        updateSelectedRow();
    }


    @Named @Produces @Audience @Key
    public Integer getaudienceId() {
        return audienceId;
    }

    public void setaudienceId(Integer audienceId) {
        this.audienceId = audienceId;
        updateSelectedRow();
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

    public Integer findNext(int campaignKey) {
        StatsListDTO<AudienceEntityStatsDTO, AudienceStatsDTO> statList =
                audienceStats.getDetails(advertiserKeyProducer.getAdvertiserKey());
        for (AudienceEntityStatsDTO entityStats : statList.getResults()) {
            if (entityStats.getEntity().getAudienceId().equals(campaignKey)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == statList.getResults().size() - 1) {
                    return null;
                }
                return statList.getResults().get(index + 1).getEntity().getAudienceId();
            }
        }
        return null;
    }

    public Integer findPrev(int campaignKey) {
        StatsListDTO<AudienceEntityStatsDTO, AudienceStatsDTO> statList =
                audienceStats.getDetails(advertiserKeyProducer.getAdvertiserKey());
        for (AudienceEntityStatsDTO entityStats : statList.getResults()) {
            if (entityStats.getEntity().getAudienceId().equals(audienceId)) {
                int index = statList.getResults().indexOf(entityStats);
                if (index == 0) {
                    return null;
                }
                return statList.getResults().get(index - 1).getEntity().getAudienceId();
            }
        }
        return null;
    }


    /**
     * Update which campaign is selected, based on the currently selected row key.
     */
    public void updateSelectedAudience() {
        if (selectedRows == null) {
            return;
        }
        if (selectedRows.isEmpty()) {
            this.audienceId = 0;
        } else {
            int rowPosition = (Integer) selectedRows.iterator().next();
            this.audienceId = audienceStats.getDetails(advertiserKeyProducer.getAdvertiserKey())
                    .getResults().get(rowPosition).getEntity().getAudienceId();
        }
    }

    /**
     * Ensure that the currently selected campaign is still visible.
     * Set to none if that is not the case.
     */
    public void updateSelectedRow() {
        if (this.selectedRows != null) {
            this.selectedRows.clear();
            List<AudienceEntityStatsDTO> campaigns =
                    audienceStats.getDetails(advertiserKeyProducer.getAdvertiserKey()).getResults();
            for (int i = 0; i < campaigns.size(); i++) {
                AudienceEntityStatsDTO campaign = campaigns.get(i);
                if (campaign.getEntity().getAudienceId().equals(getaudienceId())) {
                    this.selectedRows.add(i);
                }
            }
        }
    }

}


