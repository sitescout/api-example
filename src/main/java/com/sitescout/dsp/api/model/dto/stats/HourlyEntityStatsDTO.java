package com.sitescout.dsp.api.model.dto.stats;

import com.sitescout.dsp.api.model.dto.LinkedDTO;

import java.util.List;

public class HourlyEntityStatsDTO<T> extends LinkedDTO {
    private T entity;
    private List<HourlyStatsDTO> statsList;
    private StatsDTO totals;

    public HourlyEntityStatsDTO() {
    }

    public HourlyEntityStatsDTO(T entity, List<HourlyStatsDTO> statsList, StatsDTO totals) {
        this.entity = entity;
        this.statsList = statsList;
        this.totals = totals;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<HourlyStatsDTO> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<HourlyStatsDTO> statsList) {
        this.statsList = statsList;
    }

    public StatsDTO getTotals() {
        return totals;
    }

    public void setTotals(StatsDTO totals) {
        this.totals = totals;
    }
}
