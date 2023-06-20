package com.qa.proj1.controller;

import com.qa.proj1.entity.Currency;
import com.qa.proj1.repository.IConversionRateRepository;
import com.qa.proj1.service.CurrencyConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    public static List<Currency> currencyList = new ArrayList<>();

    @Autowired
    private final IConversionRateRepository conversionRateRepository;
    public CurrencyConvertService service;

    public MainController(IConversionRateRepository conversionRateRepository) {
        this.conversionRateRepository = conversionRateRepository;
    }


    @GetMapping("/")
    public String currencyConverterIndex(Model model) {

        model.addAttribute("currencyList", currencyList);
        return "index";
    }

    @PostMapping("/")
    public String postCurrencyConverterPage(
            @RequestParam int amount,
            @RequestParam String from,
            @RequestParam String to,
            Model model) throws Exception {
        service = new CurrencyConvertService(conversionRateRepository);
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("amount", amount);
        model.addAttribute("currencyFrom", from);
        model.addAttribute("currencyTo", to);
        double [] convertedAmountAndRate = service.convertCurrencyUsingApi(from, to, amount);

        model.addAttribute("convertedAmount", convertedAmountAndRate[0]);
        model.addAttribute("rate", convertedAmountAndRate[1]);

        return "index";
    }
}