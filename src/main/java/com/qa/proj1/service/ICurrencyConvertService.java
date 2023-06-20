package com.qa.proj1.service;

public interface ICurrencyConvertService {
    public double getConversionRateFromAPI(String from, String to) throws Exception;

    public double getConversionRateFromDB(String from, String to);
}
