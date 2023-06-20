package com.qa.proj1.service;

import com.qa.proj1.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

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
    @Nested
    class UsingDatabaseTestCases{
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
        void convertCurrencyNotFoundTest(){
            int amount = 100;
            String from = "LOL";
            String to = "WOW";
            assertThrows(AopInvocationException.class,()->service.convertCurrencyUsingDB(from, to, amount),"Currency NotFound in database");
        }
        @Test
        void convertCurrencyIfFromEqToTest(){
            int amount = 1;
            String from = "USD";
            String to = "USD";
            double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
            assertEquals(amount, (int)convertedAmountAndRate[0]);
        }
        @Test
        void convertCurrencyTest(){
            int amount = 100;
            String from = "USD";
            String to = "JPY";
            double[] convertedAmountAndRate = service.convertCurrencyUsingDB(from, to, amount);
            assertEquals(14144, (int)convertedAmountAndRate[0]);
        }
    }

    @Nested
    class UsingAPITestCases{
        @Test
        void convertCurrencyNullTest() {
            int amount = 100;
            String from = null;
            String to = null;

            double[] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);
            assertEquals(0, convertedAmountAndRate[0]);
        }
        @Test
        void convertCurrencyEmptyTest(){
            int amount = 100;
            String from = "";
            String to = "";

            double[] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);
            assertEquals(0, convertedAmountAndRate[0]);
        }

        @Test
        void convertCurrencyBlankTest(){
            int amount = 100;
            String from = "";
            String to = "";
            double[] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);
            assertEquals(0, convertedAmountAndRate[0]);
        }
        @Test
        void convertCurrencyNotFoundTest(){
            int amount = 100;
            String from = "LOL";
            String to = "WOW";
            assertThrows(HttpClientErrorException.class,()->service.convertCurrencyUsingApi(from, to, amount),"Currency NotFound in database");
        }
        @Test
        void convertCurrencyIfFromEqToTest(){
            int amount = 1;
            String from = "USD";
            String to = "USD";
            double[] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);
            assertEquals(amount, (int)convertedAmountAndRate[0]);
        }
        @Test
        void convertCurrencyTest(){
            int amount = 100;
            String from = "USD";
            String to = "JPY";
            double[] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);
            assertEquals(14187, (int)convertedAmountAndRate[0]);
        }
    }
}