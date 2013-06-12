package com.sitescout.dsp.api.model.dto.stats;

import com.sitescout.dsp.api.model.dto.ListDTO;
import com.sitescout.dsp.api.model.params.DateRange;

public class StatsListDTO<T extends GenericEntityStatsDTO<?, ?>, StatsType> extends ListDTO<T> {
    private StatsType totals;
    private DateRange dateRange;
    private Boolean fromCache;

    public StatsListDTO() {
    }

    public StatsListDTO(StatsListDTO<T, StatsType> statsListDTO) {
        super(statsListDTO);

        this.totals = statsListDTO.getTotals();
        this.dateRange = statsListDTO.getDateRange();
        this.fromCache = statsListDTO.getFromCache();
    }

    public StatsListDTO<T, StatsType> copy() {
        return new StatsListDTO<T, StatsType>(this);
    }

    public StatsType getTotals() {
        return totals;
    }

    public void setTotals(StatsType totals) {
        this.totals = totals;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Boolean getFromCache() {
        return fromCache;
    }

    public void setFromCache(Boolean fromCache) {
        this.fromCache = fromCache;
    }
}
