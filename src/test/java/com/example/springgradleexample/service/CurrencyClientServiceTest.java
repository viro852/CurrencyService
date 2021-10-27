package com.example.springgradleexample.service;


import com.example.springgradleexample.client.ExchangeClient;
import com.example.springgradleexample.pojo.CurrencyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.example.springgradleexample.prototype.CurrencyCodePrototype.aCurrencyCode;
import static com.example.springgradleexample.prototype.CurrencyCodePrototype.bCurrencyCode;
import static com.example.springgradleexample.prototype.DatePrototype.today;
import static com.example.springgradleexample.prototype.DatePrototype.yesterday;
import static com.example.springgradleexample.prototype.RatesPrototype.todayRates;
import static com.example.springgradleexample.prototype.RatesPrototype.yesterdayRates;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyClientServiceTest {

    @Value("${openexchangeratesToken}")
    private String token;

    @MockBean
    private ExchangeClient exchangeClient;

    @Autowired
    private CurrencyClientService currencyClientService;

    DateTimeFormatter dateTimeFormatter;
    String today;
    String yesterday;

    @BeforeEach
    public void setUp() throws Exception {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        today = dateTimeFormatter.format(LocalDate.now());
        yesterday = dateTimeFormatter.format(LocalDate.now().minusDays(1));
    }

    @Test
    void isTodayRatesBiggerThenYesterdayRates() {
        when(exchangeClient.getCourse(today(), token)).thenReturn(new CurrencyResponse(todayRates()));
        when(exchangeClient.getCourse(yesterday(), token)).thenReturn(new CurrencyResponse(yesterdayRates()));

        boolean trueBigger=currencyClientService.isTodayRatesBiggerThenYesterdayRates(aCurrencyCode());
        boolean falseBigger=currencyClientService.isTodayRatesBiggerThenYesterdayRates(bCurrencyCode());

        assertTrue(trueBigger);
        assertFalse(falseBigger);
    }
}