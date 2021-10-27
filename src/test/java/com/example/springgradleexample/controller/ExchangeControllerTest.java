package com.example.springgradleexample.controller;


import com.example.springgradleexample.service.CurrencyClientService;
import com.example.springgradleexample.service.GifClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static com.example.springgradleexample.prototype.CurrencyCodePrototype.aCurrencyCode;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {

    @Value("${richTag}")
    private String richTag;

    @Value("${poorTag}")
    private String poorTag;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyClientService currencyClientService;

    @MockBean
    private GifClientService gifClientService;


    @Test
    public void checkRates() throws Exception {
        when(currencyClientService.isTodayRatesBiggerThenYesterdayRates(aCurrencyCode())).thenReturn(true);
        when(gifClientService.getEmbedUrlFromRandomGifByTag(richTag)).thenReturn("richGifEmbedUrl");
        mockMvc.perform(get("/exchangerates").param("currency_code", "RUB"))
                .andExpect(status().isOk());
    }
}