package com.qa.proj1.response;

import com.google.gson.Gson;

import java.util.List;

public class ApiCurrencyListResponse {
    private String result;
    private String documentation;
    private String terms_of_use;
    private String[][] supported_codes;

    public ApiCurrencyListResponse(String result, String documentation, String terms_of_use, String[][] supported_codes) {
        this.result = result;
        this.documentation = documentation;
        this.terms_of_use = terms_of_use;
        this.supported_codes = supported_codes;
    }

    public String getResult() {
        return result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getTerms_of_use() {
        return terms_of_use;
    }

    public String[][] getSupported_codes() {
        return supported_codes;
    }
}
