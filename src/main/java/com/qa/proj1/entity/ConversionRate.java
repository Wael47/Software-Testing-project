package com.qa.proj1.entity;

import jakarta.persistence.*;

@Entity(name = "conversion_rate")
public class ConversionRate {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "currency_id_from")
    private int fromCurrency;

    @Column(name = "currency_id_to")
    private int toCurrency;
    private double rate;

    public ConversionRate(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(int fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public int getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(int toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
