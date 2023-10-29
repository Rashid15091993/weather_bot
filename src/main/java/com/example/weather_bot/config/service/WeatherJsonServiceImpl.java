package com.example.weather_bot.config.service;

import com.example.weather_bot.config.entity.GeoLocation;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.weather_bot.config.entity.Result;
import com.example.weather_bot.config.entity.Geometry;

import java.util.*;

@Slf4j
@Component
public class WeatherJsonServiceImpl implements WeatherJsonService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Value("${yandex-weather.url}")
    private String urlYandex;
    @Value("${headers-key}")
    private String weatherYandexkey;
    private String mapValue;
    @Value("${open-cage-data-url}")
    String urlGeoCode;
    @Value("${open-cage-data-key}")
    private String geoCodeKey;


    private JsonNode getJsonWeather(String fieldName, String city) {
        List<String> lonLat = getLonLat(city);
        headers.set("X-Yandex-API-Key", weatherYandexkey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlYandex)
                .queryParam("lat", lonLat.get(0))
                .queryParam("lon", lonLat.get(1));

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, requestEntity, String.class, headers);
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
        weatherMap.put("Город", getJsonWeather("geo_object", "Kaspiysk").get("locality").get("name").asText());
        //weatherMap.put("Погода сегодня", getJsonWeather("fact", "").get("temp").asText());

        for(String key : weatherMap.keySet()) {
            mapValue = weatherMap.get(key);
            stringBuilder.append(mapValue).append("\n");
        }
        return stringBuilder.toString();
    }
    private List<String> getLonLat(String city) {
        GeoLocation geoLocation = getGeoLocation(city);
        List<String> lotLatList = new ArrayList<>();

        if (geoLocation != null && geoLocation.getResults().length > 0) {
            Result result = geoLocation.getResults()[0];
            Geometry geometry = result.getGeometry();
            double latitude = geometry.getLatitude();
            double longitude = geometry.getLongitude();

            lotLatList.add(String.valueOf(latitude));
            lotLatList.add(String.valueOf(longitude));
        } else {
            System.out.println("Не удалось найти координаты для города ");
        }
        return lotLatList;
    }
    public GeoLocation getGeoLocation(String city) {

        String lan = "ru";
        String pr = "1";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlGeoCode)
                .queryParam("q", city)
                .queryParam("key", geoCodeKey)
                .queryParam("language", lan)
                .queryParam("pretty", pr);

        ResponseEntity<GeoLocation> response = restTemplate.getForEntity(builder.toUriString(), GeoLocation.class);
        return response.getBody();
    }
}
