package com.example.springgradleexample.client;

import com.example.springgradleexample.pojo.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange-client", url = "${openExchangeRatesUrl}")
public interface ExchangeClient {
    @GetMapping(path = "historical/{date}.json")
    CurrencyResponse getCourse(@PathVariable String date, @RequestParam(name = "app_id") String appId);
}
