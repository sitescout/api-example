package com.sitescout.dsp.api.model.dto.stats;

public class HourlyStatsDTO {
    private int hour;
    private StatsDTO stats;

    public HourlyStatsDTO() {
    }

    public int getHour() {
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
