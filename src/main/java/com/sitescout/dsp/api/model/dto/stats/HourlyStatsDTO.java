package com.sitescout.dsp.api.model.dto.stats;

public class HourlyStatsDTO {
    private Integer hour;
    private StatsDTO stats;

    public HourlyStatsDTO() {
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public StatsDTO getStats() {
        return stats;
    }

    public void setStats(StatsDTO stats) {
        this.stats = stats;
    }
}
