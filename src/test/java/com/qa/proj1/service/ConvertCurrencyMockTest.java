package com.qa.proj1.service;

import com.qa.proj1.service.ICurrencyConvertService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConvertCurrencyMockTest {
    @Mock
    ICurrencyConvertService service;
    @BeforeEach
    void init(){
        service = mock(ICurrencyConvertService.class);
    }
    @Test
    void getConversionRateFromMockApiTest() throws Exception {
        when(service.getConversionRateFromAPI("USD","ILS")).thenReturn(3.70);
        assertEquals(3.70, service.getConversionRateFromAPI("USD","ILS"));
    }
}
