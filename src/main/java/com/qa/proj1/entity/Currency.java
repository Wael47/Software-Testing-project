package com.qa.proj1.entity;

import jakarta.persistence.*;

@Entity(name = "currency_names")
public class Currency {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "code")
    private String currencyCode;
    @Column(name = "name_string")

    private String currencyName;

    public Currency(String currencyCode, String currencyName){
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
    }

    public Currency() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
