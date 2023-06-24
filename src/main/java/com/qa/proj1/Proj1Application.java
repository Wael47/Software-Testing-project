package com.qa.proj1;

import com.google.gson.Gson;
import com.qa.proj1.controller.MainController;
import com.qa.proj1.response.ApiCurrencyListResponse;
import com.qa.proj1.entity.Currency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Proj1Application {
    public static void main(String[] args) {
        String apiUrl = "https://v6.exchangerate-api.com/v6/2ee3142def0f1792c6871a81 /codes";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(apiUrl, String.class);
        Gson gson = new Gson();
        ApiCurrencyListResponse response = gson.fromJson(result, ApiCurrencyListResponse.class);
        for (String[] currency : response.getSupported_codes()) {
            MainController.currencyList.add(new Currency(currency[0], currency[1]));
        }
        SpringApplication.run(Proj1Application.class, args);
    }
}