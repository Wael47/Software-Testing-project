package com.qa.proj1.service;

public interface ICurrencyConvertService {
    public double getConversionRateFromAPI(String from, String to);

    public double getConversionRateFromDB(String from, String to);
}
