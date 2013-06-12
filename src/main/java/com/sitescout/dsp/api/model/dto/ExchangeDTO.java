package com.sitescout.dsp.api.model.dto;

public class ExchangeDTO {
    private Integer exchangeId;
    private String name;

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExchangeDTO that = (ExchangeDTO) o;

        if (exchangeId != null ? !exchangeId.equals(that.exchangeId) : that.exchangeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return exchangeId != null ? exchangeId.hashCode() : 0;
    }
}
