package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.CampaignDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.data.CampaignDetails;
import com.sitescout.ui.data.CampaignStats;
import com.sitescout.ui.qualifiers.Advertiser;
import com.sitescout.ui.qualifiers.Campaign;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The main page, allowing selection of campaigns.
 * This page will also validate which detail page the user is on (Hourly, Site, Creative, Offer)
 * Based on the validation, it will grab appropriate data for creating graphs.
 *
 * @author sean
 */
@Named
@ConversationScoped
public class CampaignsPage implements Serializable {

    @Inject
    CampaignStats campaignStats;
    @Inject
    CampaignDetails campaignDetails;

    @Inject @Advertiser @Key
    int advertiserKey;

    enum detailType {hourly, sites, creatives, offers}

    detailType type = detailType.hourly;
    Collection<Object> selectedRow;
    Integer prevSelectedCampaignKey;
    Integer campaignKey = 0;
    StatsListDTO<EntityStatsDTO<CampaignDTO>, StatsDTO> statsList;
    @Inject
    private Conversation conversation;

    public String getCid() {
        return conversation.getId();
    }

    public String startConvo() {
        if (conversation.isTransient()) {
            conversation.begin();
            return "index";
        }
        return "index";
    }

    public String campaignKeyValidator() {
        if (campaignKey == 0) {
            return null;
        }
        return "load";
    }

    public Integer setCampaignKey() throws IOException {
        int rowPosition = (Integer) selectedRow.iterator().next();
        this.campaignKey = campaignStats.getStatsList().getResults().get(rowPosition).getEntity().getCampaignId();
        return campaignKey;
    }

    @Named
    @Produces
    @Campaign
    @Key
    public Integer getCampaignKey() {
        return campaignKey;
    }

    public boolean hourly() {
        return type == detailType.hourly;
    }

    public boolean sites() {
        return type == detailType.sites;
    }

    public boolean creatives() {
        return type == detailType.creatives;
    }

    public boolean offers() {
        return type == detailType.offers;
    }

    public void setTableSites() {
        this.type = detailType.sites;
    }

    public void setTableCreatives() {
        this.type = detailType.creatives;
    }

    public void setTableOffers() {
        this.type = detailType.offers;
    }

    public void setTableHourly() {
        this.type = detailType.hourly;
    }

    public Collection<Object> getSelectedRow() throws IOException {
        if (statsList != campaignStats.getStatsList()) {
            statsList = campaignStats.getStatsList();
            prevSelectedCampaignKey = campaignKey;
            for (EntityStatsDTO<CampaignDTO> entityStats : campaignStats.getDetails(advertiserKey).getResults()) {
                if (entityStats.getEntity().getCampaignId().equals(prevSelectedCampaignKey)) {
                    int index = campaignStats.getStatsList().getResults().indexOf(entityStats);
                    Collection<Object> row = new ArrayList();
                    row.add(index);
                    this.selectedRow = row;
                    return row;
                }
            }
            setSelectedRow(null);
            return null;
        }
        return selectedRow;
    }


    public void setSelectedRow(Collection<Object> selectedCampaignKeys) {
        this.selectedRow = selectedCampaignKeys;
    }

    public HourlyEntityStatsDTO<CampaignDTO> getHourlyDetail(int advertiserKey, int campaignKey) throws IOException {
        return campaignDetails.getDetails(advertiserKey, campaignKey);
    }
}


