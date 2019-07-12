package com.nhs.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;

public class CustomerSubmission {

    @NotNull(message = "RegularAmount required")
    @Valid
    private RegularAmount regularAmount;    

    public RegularAmount getRegularAmount() {
        return regularAmount;
    }

    public void setRegularAmount(RegularAmount regularAmount) {
        this.regularAmount = regularAmount;
    }
  
}