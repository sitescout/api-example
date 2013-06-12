package com.sitescout.dsp.api.model.dto.stats;

public class CampaignStatsListDTO<T> extends StatsListDTO<EntityStatsDTO<T>, StatsDTO> {
    public CampaignStatsListDTO() {
        super();
        setTotals(new StatsDTO());
    }

    public CampaignStatsListDTO(CampaignStatsListDTO<T> campaignStatsListDTO) {
        super(campaignStatsListDTO);
    }

    public CampaignStatsListDTO<T> copy() {
        return new CampaignStatsListDTO<T>(this);
    }
}
