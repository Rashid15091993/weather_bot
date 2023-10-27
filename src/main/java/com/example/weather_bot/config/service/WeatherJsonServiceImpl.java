package com.example.weather_bot.config.service;

import com.example.weather_bot.config.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WeatherJsonServiceImpl implements WeatherJsonService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Value("${yandex-weather.url}")
    private String URL;
    @Value("${headers-key}")
    private String headersKey;
    private String mapValue;

    private JsonNode getJsonWeather(String fieldName) {
        headers.set("X-Yandex-API-Key", headersKey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                URL, HttpMethod.GET, requestEntity, String.class, headers);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        log.info(response.getBody());
        try {
            root = mapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return root.get(fieldName);
    }

    public String getWeatherCity() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> weatherMap = new HashMap<>();
        weatherMap.put("Город", getJsonWeather("geo_object").get("locality").get("name").asText());
        weatherMap.put("Погода сегодня", getJsonWeather("fact").get("temp").asText());

        for(String key : weatherMap.keySet()) {
            mapValue = weatherMap.get(key);
            stringBuilder.append(mapValue).append("\n");
        }
        return stringBuilder.toString();
    }
}
