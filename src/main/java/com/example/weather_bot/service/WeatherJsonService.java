package com.example.weather_bot.service;

import com.example.weather_bot.entity.dto.WeatherDto;

public interface WeatherJsonService {
    String getWeatherCity(String city);
    WeatherDto createWeatherDto(String cityName);
}
