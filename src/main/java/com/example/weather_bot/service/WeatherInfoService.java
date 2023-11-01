package com.example.weather_bot.service;

import com.example.weather_bot.entity.dto.WeatherDto;

public interface WeatherInfoService {
    String getWeatherInfo(WeatherDto weatherDto);
}
