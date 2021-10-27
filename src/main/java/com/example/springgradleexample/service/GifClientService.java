package com.example.springgradleexample.service;

import com.example.springgradleexample.client.GifClient;
import com.example.springgradleexample.pojo.GifResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifClientService {
    @Value("${giphyToken}")
    private String giphyToken;
    private GifClient gifClient;

    @Autowired
    public GifClientService(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    public String getEmbedUrlFromRandomGifByTag(String tag) {
        GifResponse gifByid = gifClient.getRandomGifByTag(giphyToken, tag);
        return getEmbedUrlFromGif(gifByid);
    }

    public String getEmbedUrlFromGif(GifResponse gifById) {
        return gifById.getData().getEmbed_url();
    }
}