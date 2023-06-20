package com.qa.proj1.service;

import com.qa.proj1.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyConvertServiceTest {


    @Autowired
    private IConversionRateRepository conversionRateRepository;

    private CurrencyConvertService service;

    @BeforeEach
    void init(){
        service = new CurrencyConvertService(conversionRateRepository);
    }

    @Test
    void convertCurrencyNullTest(){
        int amount = 100;
        String from = null;
        String to = null;

        double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
        assertEquals(0, convertedAmountAndRate[0]);
    }
    @Test
    void convertCurrencyEmptyTest(){
        int amount = 100;
        String from = "";
        String to = "";

        double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
        assertEquals(0, convertedAmountAndRate[0]);
    }
    @Test
    void convertCurrencyBlankTest(){
        int amount = 100;
        String from = "";
        String to = "";
        double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
        assertEquals(0, convertedAmountAndRate[0]);
    }

    @Test
    void convertCurrencyNotFoundDBTest(){
        int amount = 100;
        String from = "LOL";
        String to = "WOW";
        assertThrows(AopInvocationException.class,()->service.convertCurrencyUsingDB(from, to, amount),"Currency NotFound in database");
    }
    @Test
    void convertCurrencyUsingDBIfFromEqToTest(){
        int amount = 1;
        String from = "USD";
        String to = "USD";
        double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
        assertEquals(1, (int)convertedAmountAndRate[0]);
    }
    @Test
    void convertCurrencyUsingDBTest(){
        int amount = 100;
        String from = "USD";
        String to = "JPY";
        double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
        assertEquals(14144, (int)convertedAmountAndRate[0]);
    }
}