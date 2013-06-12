package com.sitescout.dsp.api.model.params;

public class Sorting {
    private String sortBy;
    private SortDirection sortDirection;

    public Sorting(String sortBy, SortDirection sortDirection) {
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
}
