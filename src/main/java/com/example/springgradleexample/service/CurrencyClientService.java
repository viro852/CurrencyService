package com.example.springgradleexample.service;

import com.example.springgradleexample.client.ExchangeClient;
import com.example.springgradleexample.pojo.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyClientService {
    @Value("${openexchangeratesToken}")
    private String token;

    private final ExchangeClient exchangeClient;

    @Autowired
    public CurrencyClientService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public boolean isTodayRatesBiggerThenYesterdayRates(String requiredCurrencyCode) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = dateTimeFormatter.format(LocalDate.now());
        String yesterday = dateTimeFormatter.format(LocalDate.now().minusDays(1));

        CurrencyResponse currencyResponseToday=exchangeClient.getCourse(today, token);
        CurrencyResponse currencyResponseYesterday=exchangeClient.getCourse(yesterday, token);

        Double requiredCurrencyTodayRates=currencyResponseToday.getRates().get(requiredCurrencyCode);
        Double requiredCurrencyYesterdayRates=currencyResponseYesterday.getRates().get(requiredCurrencyCode);
        return requiredCurrencyTodayRates > requiredCurrencyYesterdayRates;
    }
}

