package com.ylysenkova.movieland.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    @JsonProperty("cc")
    private Currency currency;
    private BigDecimal rate;

    public Rate() {
    }

    public Rate(Currency cc, BigDecimal rate) {
        this.currency = cc;
        this.rate = rate;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}
