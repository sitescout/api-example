package com.sitescout.dsp.api.model.dto;

import com.sitescout.dsp.api.model.params.Pagination;
import com.sitescout.dsp.api.model.params.Sorting;

import java.util.ArrayList;
import java.util.List;

public class ListDTO<T> extends LinkedDTO {
    private int totalCount;
    private Pagination pagination;
    private Sorting sorting;
    private List<T> results;
    private String filter;
    private String status;

    public ListDTO() {
        this.results = new ArrayList<T>();
    }

    public ListDTO(ListDTO listDTO) {
        this.setResults(new ArrayList<T>(listDTO.getResults()));
        this.setTotalCount(listDTO.getTotalCount());
        this.setPagination(listDTO.getPagination());
        this.setSorting(listDTO.getSorting());
        this.setFilter(listDTO.getFilter());
        this.setStatus(listDTO.getStatus());
    }

    public ListDTO<T> copy() {
        return new ListDTO<T>(this);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Sorting getSorting() {
        return sorting;
    }

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
        this.totalCount = results != null ? results.size() : 0;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //public void setStatus(AssetStatus status) {
    //  this.status = status != null ? status.name() : null;
    //}
}
