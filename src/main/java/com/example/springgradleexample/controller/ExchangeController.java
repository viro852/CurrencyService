package com.example.springgradleexample.controller;

import com.example.springgradleexample.service.CurrencyClientService;
import com.example.springgradleexample.service.GifClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {
    @Value("${richTag}")
    private String richTag;

    @Value("${poorTag}")
    private String poorTag;

    private final CurrencyClientService currencyClientService;

    private final GifClientService gifClientService;

    @Autowired
    public ExchangeController(CurrencyClientService currencyClientService, GifClientService gifClientService) {
        this.currencyClientService = currencyClientService;
        this.gifClientService = gifClientService;
    }

    @GetMapping("/exchangerates")
    public ResponseEntity checkRates(@RequestParam String currency_code) {
        String richGifEmbedUrl=gifClientService.getEmbedUrlFromRandomGifByTag(richTag);
        String poorGifEmbedUrl=gifClientService.getEmbedUrlFromRandomGifByTag(poorTag);
        if (currencyClientService.isTodayRatesBiggerThenYesterdayRates(currency_code)) {
            return new ResponseEntity("" +
                    "<html>" +
                    "<body>" +
                    "<iframe src=\"" + poorGifEmbedUrl + "\" width=\"480\" height=\"356\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen></iframe>" +
                    "<body>" +
                    "<html>", HttpStatus.OK);
        } else {
            return new ResponseEntity("" +
                    "<html>" +
                    "<body>" +
                    "<iframe src=\"" + richGifEmbedUrl + "\" width=\"480\" height=\"356\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen></iframe>" +
                    "<body>" +
                    "<html>", HttpStatus.OK);
        }
    }
}
