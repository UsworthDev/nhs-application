package com.nhs.demo.model;

import javax.validation.constraints.NotNull;

import com.nhs.demo.enums.Frequency;
import com.nhs.demo.validator.ValidRegularAmount;

@ValidRegularAmount
public class RegularAmount {
    private Frequency frequency;
    private String amount;

    @NotNull(message = "Frequency required")
    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @NotNull(message = "Amount required")       
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

