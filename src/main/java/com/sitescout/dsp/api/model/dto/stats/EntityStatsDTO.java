package com.sitescout.dsp.api.model.dto.stats;

public class EntityStatsDTO<T> extends GenericEntityStatsDTO<T, StatsDTO> {

    public EntityStatsDTO() {

    }

    public EntityStatsDTO(T entity, StatsDTO stats) {
        super(entity, stats);
    }
}
