package com.sitescout.dsp.api.model.dto.stats;

import com.sitescout.dsp.api.util.csv.CsvType;

public class AudienceStatsDTO {
    @CsvType(CsvType.Value.NUMBER)
    private int newVisitors;
    @CsvType(CsvType.Value.NUMBER)
    private int totalVisitors;

    public int getNewVisitors() {
        return newVisitors;
    }

    public void setNewVisitors(int newVisitors) {
        this.newVisitors = newVisitors;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }
}
