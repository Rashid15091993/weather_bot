package com.example.weather_bot.config.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeatherJsonServiceImpl implements WeatherJsonService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Value("${yandex-weather.url}")
    private String URL;
    @Value("${headers-key}")
    private String headersKey;
    @Override
    public void getJsonWeather() {
        headers.set("X-Yandex-API-Key", headersKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                URL, HttpMethod.GET, requestEntity, String.class, headers);
        log.info(response.getBody());
    }
}
