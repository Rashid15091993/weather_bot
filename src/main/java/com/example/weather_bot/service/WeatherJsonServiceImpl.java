package com.example.weather_bot.service;

import com.example.weather_bot.entity.*;
import com.example.weather_bot.entity.dto.WeatherDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
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

    @Autowired
    WeatherInfoService weatherInfoService;


    private JsonNode getJsonWeather(String fieldName, String city) {
        List<String> lonLat = getLonLat(city);
        headers.set("X-Yandex-API-Key", weatherYandexkey);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlYandex)
                .queryParam("lat", lonLat.get(0))
                .queryParam("lon", lonLat.get(1))
                .queryParam("lang", "ru_RU");

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
    public String getWeatherCity(String city) {
        WeatherDto weatherDto = createWeatherDto(city);
        return weatherInfoService.getWeatherInfo(weatherDto);
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
            log.info("Не удалось найти координаты для города ");
        }
        return lotLatList;
    }
    public WeatherDto createWeatherDto(String cityName) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCity(getJsonWeather("geo_object", cityName).get("locality").get("name").asText());
        weatherDto.setTempFact(getJsonWeather("fact", cityName).get("temp").asText());
        weatherDto.setPrecStrength(getJsonWeather("fact", cityName).get("prec_strength").asText());
        weatherDto.setPrecType(getJsonWeather("fact", cityName).get("prec_type").asText());
        weatherDto.setSeason(getJsonWeather("fact", cityName).get("season").asText());
        return weatherDto;
    }
    public GeoLocation getGeoLocation(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlGeoCode).encode(StandardCharsets.US_ASCII)
                .queryParam("q", city)
                .queryParam("key", geoCodeKey);

        ResponseEntity<GeoLocation> response = restTemplate.getForEntity(builder.toUriString(), GeoLocation.class);
        return response.getBody();
    }
}
