package com.sitescout.dsp.api.model.params;

public class Pagination {
    private Integer page;
    private Integer pageSize;

    public Pagination() {
    }

    public Pagination(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
