package com.qa.proj1.service;

import com.qa.proj1.service.ICurrencyConvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ConvertCurrencyMockTest {
    @Mock
    ICurrencyConvertService service;

    @BeforeEach
    void init() {
        service = mock(ICurrencyConvertService.class);
    }

    @Test
    void getConversionRateNullTest() {
        String from = null;
        String to = null;
        when(service.getConversionRateFromAPI(null, null)).thenReturn(0.0);
        assertEquals(0.0, service.getConversionRateFromAPI(from, to));
    }

    @Test
    void convertCurrencyEmptyTest() {
        String from = "";
        String to = "";
        when(service.getConversionRateFromAPI("", "")).thenReturn(0.0);
        assertEquals(0.0, service.getConversionRateFromAPI(from, to));
    }

    @Test
    void convertCurrencyBlankTest() {
        String from = " ";
        String to = " ";
        when(service.getConversionRateFromAPI(" ", " ")).thenReturn(0.0);
        assertEquals(0.0, service.getConversionRateFromAPI(from, to));
    }

    @Test
    void convertCurrencyNotFoundTest() {
        doThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Currency NotFound")).when(service).getConversionRateFromAPI(anyString(), anyString());
        assertThrows(HttpClientErrorException.class, () -> service.getConversionRateFromAPI("from", "to"), "Currency NotFound");

    }

    @Test
    void convertCurrencyIfFromEqToTest() {
        String from = "USD";
        String to = "USD";
        when(service.getConversionRateFromAPI("USD", "USD")).thenReturn(1.0);
        assertEquals(1.0, service.getConversionRateFromAPI(from, to));

    }

    @Test
    void getConversionRateTest() {
        String from = "USD";
        String to = "ILS";
        when(service.getConversionRateFromAPI("USD", "ILS")).thenReturn(3.70);
        double rate = service.getConversionRateFromAPI("USD", "ILS");
        assertEquals(3.70, rate);
    }
}
/*
* when(service.getConversionRateFromAPI("USD", "USD")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("USD", "EUR")).thenReturn(0.914);
        when(service.getConversionRateFromAPI("USD", "GBP")).thenReturn(0.781);
        when(service.getConversionRateFromAPI("USD", "JPY")).thenReturn(141.45);
        when(service.getConversionRateFromAPI("USD", "CAD")).thenReturn(1.32);
        when(service.getConversionRateFromAPI("USD", "AUD")).thenReturn(1.45);
        when(service.getConversionRateFromAPI("USD", "INR")).thenReturn(81.94);
        when(service.getConversionRateFromAPI("USD", "CNY")).thenReturn(7.13);
        when(service.getConversionRateFromAPI("GBP", "USD")).thenReturn(1.28);
        when(service.getConversionRateFromAPI("GBP", "EUR")).thenReturn(1.17);
        when(service.getConversionRateFromAPI("GBP", "GBP")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("GBP", "JPY")).thenReturn(181.29);
        when(service.getConversionRateFromAPI("GBP", "CAD")).thenReturn(1.69);
        when(service.getConversionRateFromAPI("GBP", "AUD")).thenReturn(1.86);
        when(service.getConversionRateFromAPI("GBP", "INR")).thenReturn(104.85);
        when(service.getConversionRateFromAPI("GBP", "CNY")).thenReturn(9.13);
        when(service.getConversionRateFromAPI("EUR", "USD")).thenReturn(1.09);
        when(service.getConversionRateFromAPI("EUR", "EUR")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("EUR", "GBP")).thenReturn(0.854);
        when(service.getConversionRateFromAPI("EUR", "JPY")).thenReturn(154.6);
        when(service.getConversionRateFromAPI("EUR", "CAD")).thenReturn(1.45);
        when(service.getConversionRateFromAPI("EUR", "AUD")).thenReturn(1.59);
        when(service.getConversionRateFromAPI("EUR", "INR")).thenReturn(89.62);
        when(service.getConversionRateFromAPI("EUR", "CNY")).thenReturn(7.79);
        when(service.getConversionRateFromAPI("INR", "USD")).thenReturn(0.0122);
        when(service.getConversionRateFromAPI("INR", "EUR")).thenReturn(0.0112);
        when(service.getConversionRateFromAPI("INR", "GBP")).thenReturn(0.00954);
        when(service.getConversionRateFromAPI("INR", "JPY")).thenReturn(1.72);
        when(service.getConversionRateFromAPI("INR", "CAD")).thenReturn(0.0161);
        when(service.getConversionRateFromAPI("INR", "AUD")).thenReturn(0.0178);
        when(service.getConversionRateFromAPI("INR", "INR")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("INR", "CNY")).thenReturn(0.087);
        when(service.getConversionRateFromAPI("CAD", "USD")).thenReturn(0.757);
        when(service.getConversionRateFromAPI("CAD", "EUR")).thenReturn(0.692);
        when(service.getConversionRateFromAPI("CAD", "GBP")).thenReturn(0.591);
        when(service.getConversionRateFromAPI("CAD", "JPY")).thenReturn(107.06);
        when(service.getConversionRateFromAPI("CAD", "CAD")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("CAD", "AUD")).thenReturn(1.1);
        when(service.getConversionRateFromAPI("CAD", "INR")).thenReturn(61.97);
        when(service.getConversionRateFromAPI("CAD", "CNY")).thenReturn(5.4);
        when(service.getConversionRateFromAPI("CNY", "USD")).thenReturn(0.14);
        when(service.getConversionRateFromAPI("CNY", "EUR")).thenReturn(0.128);
        when(service.getConversionRateFromAPI("CNY", "GBP")).thenReturn(0.11);
        when(service.getConversionRateFromAPI("CNY", "JPY")).thenReturn(19.84);
        when(service.getConversionRateFromAPI("CNY", "CAD")).thenReturn(0.185);
        when(service.getConversionRateFromAPI("CNY", "AUD")).thenReturn(0.204);
        when(service.getConversionRateFromAPI("CNY", "INR")).thenReturn(11.5);
        when(service.getConversionRateFromAPI("CNY", "CNY")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("AUD", "USD")).thenReturn(0.688);
        when(service.getConversionRateFromAPI("AUD", "EUR")).thenReturn(0.628);
        when(service.getConversionRateFromAPI("AUD", "GBP")).thenReturn(0.537);
        when(service.getConversionRateFromAPI("AUD", "JPY")).thenReturn(97.17);
        when(service.getConversionRateFromAPI("AUD", "CAD")).thenReturn(0.908);
        when(service.getConversionRateFromAPI("AUD", "AUD")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("AUD", "INR")).thenReturn(56.31);
        when(service.getConversionRateFromAPI("AUD", "CNY")).thenReturn(4.9);
        when(service.getConversionRateFromAPI("JPY", "USD")).thenReturn(0.00707);
        when(service.getConversionRateFromAPI("JPY", "EUR")).thenReturn(0.00647);
        when(service.getConversionRateFromAPI("JPY", "GBP")).thenReturn(0.00552);
        when(service.getConversionRateFromAPI("JPY", "JPY")).thenReturn(1.0);
        when(service.getConversionRateFromAPI("JPY", "CAD")).thenReturn(0.00934);
        when(service.getConversionRateFromAPI("JPY", "AUD")).thenReturn(0.0103);
        when(service.getConversionRateFromAPI("JPY", "INR")).thenReturn(0.581);
        when(service.getConversionRateFromAPI("JPY", "CNY")).thenReturn(0.0504);
*/