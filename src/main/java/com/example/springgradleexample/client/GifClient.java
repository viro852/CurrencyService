package com.example.springgradleexample.client;


import com.example.springgradleexample.pojo.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-client",url = "${giphyUrl}")
public interface GifClient {
    @GetMapping(path = "/random",consumes = MediaType.APPLICATION_JSON_VALUE)
    GifResponse getRandomGifByTag(@RequestParam(value = "api_key") String appId, @RequestParam(name="tag") String tag);
}
