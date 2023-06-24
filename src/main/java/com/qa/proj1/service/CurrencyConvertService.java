package com.qa.proj1.service;

import com.google.gson.Gson;
import com.qa.proj1.repository.IConversionRateRepository;
import com.qa.proj1.response.ApiConversionRateResponse;
import org.springframework.aop.AopInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Service
public class CurrencyConvertService implements ICurrencyConvertService {

    private IConversionRateRepository conversionRateRepository;

    public double convertCurrency(int amount, double conversionRate) {
        return amount * conversionRate;
    }
    @Override
    public double getConversionRateFromAPI(String from, String to) {
        try {
            String apiUrl = "https://v6.exchangerate-api.com/v6/2ee3142def0f1792c6871a81/pair/" + from.toUpperCase() + "/" + to.toUpperCase();
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(apiUrl, String.class);
            Gson gson = new Gson();
            ApiConversionRateResponse response = gson.fromJson(result, ApiConversionRateResponse.class);
            return response.getConversion_rate();
        }catch (HttpClientErrorException.NotFound e){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Currency NotFound");
        }
    }

    @Override
    public double getConversionRateFromDB(String from, String to) {
        try {
            return conversionRateRepository.getConversionRate(from,to);
        }catch (AopInvocationException e){
            throw  new AopInvocationException("Currency NotFound in database");
        }
    }

    public double[] convertCurrencyUsingApi(String from, String to, int amount) {
        if (from == null || from.isBlank() || to == null || to.isBlank()){
            return new double[]{0,0};
        }
        double convertedAmount, conversionRate;
        from = from.toUpperCase();
        to = to.toUpperCase();
        if (from.equals(to)){
            conversionRate = 1;
            convertedAmount = amount;
            return new double[]{convertedAmount, conversionRate};
        }
        conversionRate =  getConversionRateFromAPI(from, to);
        convertedAmount = convertCurrency(amount,conversionRate);
        return new double[]{convertedAmount, conversionRate};
    }

    public double[] convertCurrencyUsingDB(String from, String to, int amount){
        if (from == null || from.isBlank() || to == null || to.isBlank()){
            return new double[]{0,0};
        }
        double convertedAmount, conversionRate;
        from = from.toUpperCase();
        to = to.toUpperCase();
        if (from.equals(to)){
            conversionRate = 1;
            convertedAmount = amount;
            return new double[]{convertedAmount, conversionRate};
        }
        conversionRate =  getConversionRateFromDB(from, to);
        convertedAmount = convertCurrency(amount,conversionRate);
        return new double[]{convertedAmount, conversionRate};
    }

    public void setConversionRateRepository(IConversionRateRepository conversionRateRepository) {
        this.conversionRateRepository = conversionRateRepository;
    }
}
