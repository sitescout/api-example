package com.sitescout.ui.page;

import com.sitescout.dsp.api.model.dto.campaign.SiteRuleDTO;
import com.sitescout.dsp.api.model.dto.stats.EntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.HourlyEntityStatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsDTO;
import com.sitescout.dsp.api.model.dto.stats.StatsListDTO;
import com.sitescout.ui.AdvertiserKeyProducer;
import com.sitescout.ui.data.SiteHourly;
import com.sitescout.ui.data.SiteStats;
import com.sitescout.ui.qualifiers.Key;
import com.sitescout.ui.qualifiers.SiteReference;

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
 * The main page, allowing selection of Sites.
 * Based on the selection, it will allow further navigation options: (Hourly, Placements, Creatives)
 */
@Named
@ConversationScoped
public class SitesPage implements Serializable {

    @Inject SiteStats siteStats;
    @Inject SiteHourly siteHourly;
    @Inject AdvertiserKeyProducer advertiserKeyProducer;
    @Inject CampaignsPage campaignsPage;

    String siteRef;
    Collection<Object> selectedRows;

    public void campaignKeyObserver(@Observes CampaignsPage.CampaignKeyChangeEvent event) {
        setNull();
    }

    public void advertiserKeyObserver(@Observes AdvertiserKeyProducer.AdvertiserKeyChangeEvent event) {
        setNull();
    }

    public void validateSelectedCampaign(@Observes CalendarBean.DateChangeEvent event) {
        updateSelectedRow();
    }

    @Named @Produces @SiteReference @Key
    public String getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
        updateSelectedRow();
    }

    public Collection<Object> getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(Collection<Object> selectedCampaignKeys) {
        this.selectedRows = selectedCampaignKeys;
    }

    public String findNext(String siteRef) {
        StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> statList =
                siteStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey());
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
        StatsListDTO<EntityStatsDTO<SiteRuleDTO>, StatsDTO> statList =
                siteStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey());
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

    public void setNull() {
        setSelectedRows(null);
        setSiteRef(null);
    }

    /**
     * Update which site is selected, based on the currently selected row key.
     */
    public void updateSelectedSite() {
        if (selectedRows == null) {
            this.siteRef = null;
        } else {
            int rowPosition = (Integer) selectedRows.iterator().next();
            this.siteRef = siteStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey())
                    .getResults().get(rowPosition).getEntity().getSiteRef();
        }
    }

    /**
     * Ensure that the currently selected campaign is still visible.
     * Set to none if that is not the case.
     */
    public void updateSelectedRow() {
        if (this.selectedRows != null) {
            this.selectedRows.clear();
            List<EntityStatsDTO<SiteRuleDTO>> campaigns =
                    siteStats.getDetails(advertiserKeyProducer.getAdvertiserKey(), campaignsPage.getCampaignKey()).getResults();
            for (int i = 0; i < campaigns.size(); i++) {
                EntityStatsDTO<SiteRuleDTO> campaign = campaigns.get(i);
                if (campaign.getEntity().getSiteRef().equals(getSiteRef())) {
                    this.selectedRows.add(i);
                }
            }
        }
    }


    public HourlyEntityStatsDTO<SiteRuleDTO> getSelectedSite(String siteRef, int advertiserKey, int campaignKey) throws IOException {
        return siteHourly.getDetails(siteRef, advertiserKey, campaignKey);
    }
}
