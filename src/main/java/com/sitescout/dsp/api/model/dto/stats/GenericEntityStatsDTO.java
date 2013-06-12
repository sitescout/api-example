package com.sitescout.dsp.api.model.dto.stats;

import com.sitescout.dsp.api.model.dto.LinkedDTO;

public abstract class GenericEntityStatsDTO<Entity, Stats> extends LinkedDTO {
    // ATTN: If you change the names of these properties, also change stats sorting, which is based on them
    private Entity entity;
    private Stats stats;

    public GenericEntityStatsDTO() {
    }

    public GenericEntityStatsDTO(Entity entity, Stats stats) {
        this.entity = entity;
        this.stats = stats;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
