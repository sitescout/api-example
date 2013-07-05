package com.sitescout.dsp.api.model.dto.campaign;


import com.sitescout.dsp.api.type.SpendCapType;

public class BudgetDTO {
    public static final double MAX_AMOUNT = 100000.0;

    private Double amount;
    private SpendCapType type;
    private Boolean evenDeliveryEnabled;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SpendCapType getType() {
        return type;
    }

    public void setType(SpendCapType type) {
        this.type = type;
    }

    public Boolean getEvenDeliveryEnabled() {
        return evenDeliveryEnabled;
    }

    public void setEvenDeliveryEnabled(Boolean evenDeliveryEnabled) {
        this.evenDeliveryEnabled = evenDeliveryEnabled;
    }
}
