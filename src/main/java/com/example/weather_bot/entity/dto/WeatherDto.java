package com.example.weather_bot.entity.dto;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

//@Cacheable(cacheNames="weather")
@Data
public class WeatherDto {
    private String city;
    private String tempFact;
    private String precStrength;
    private String season;
}
