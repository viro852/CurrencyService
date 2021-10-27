package com.example.springgradleexample.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CurrencyResponse {
    private String base;
    private Map<String, Double> rates;

    public CurrencyResponse(Map<String, Double> rates) {
        this.rates = rates;
    }

}
