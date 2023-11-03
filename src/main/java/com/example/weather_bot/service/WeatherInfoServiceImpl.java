package com.example.weather_bot.service;

import com.example.weather_bot.entity.PrecEnum;
import com.example.weather_bot.entity.PrecTypeEnum;
import com.example.weather_bot.entity.SeasonEnum;
import com.example.weather_bot.entity.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherInfoServiceImpl implements WeatherInfoService {
    @Override
    public String getWeatherInfo(WeatherDto weatherDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Населенный пункт -> ").append(weatherDto.getCity()).append("\n");
        stringBuilder.append("Температура сейчас -> ").append(weatherDto.getTempFact()).append("\n");
        stringBuilder.append("Тип осадков -> ").append(getPrecipitationType(weatherDto.getPrecType())).append("\n");
        stringBuilder.append("Сила осадков -> ").append(getPrecipitation(weatherDto.getPrecStrength())).append("\n");
        stringBuilder.append("Время года -> ").append(getSeasonRus(weatherDto.getSeason())).append("\n");
        return stringBuilder.toString();
    }

    private String getPrecipitation(String precipitationNum) {
        Map<String, String> precipitationMap = new HashMap<>();
        precipitationMap.put("0", PrecEnum.NO_PRECIPITATION.toString());
        precipitationMap.put("0.25", PrecEnum.LIGHT_RAIN.toString());
        precipitationMap.put("0.25", PrecEnum.LIGHT_SNOW.toString());
        precipitationMap.put("0.5", PrecEnum.RAIN.toString());
        precipitationMap.put("0.5", PrecEnum.SNOW.toString());
        precipitationMap.put("0.75", PrecEnum.HEAVY_RAIN.toString());
        precipitationMap.put("0.75", PrecEnum.HEAVY_SNOW.toString());
        precipitationMap.put("1", PrecEnum.HEAVY_DOWNPOUR.toString());
        precipitationMap.put("1", PrecEnum.VERY_HEAVY_SNOW.toString());
        return precipitationMap.get(precipitationNum);
    }
    private String getPrecipitationType(String precipitationNum) {
        Map<String, String> precipitationMap = new HashMap<>();
        precipitationMap.put("0", PrecTypeEnum.NO_PRECIPITATION.toString());
        precipitationMap.put("1", PrecTypeEnum.RAIN.toString());
        precipitationMap.put("2", PrecTypeEnum.RAIN_AND_SNOW.toString());
        precipitationMap.put("3", PrecTypeEnum.SNOW.toString());
        precipitationMap.put("4", PrecTypeEnum.HAIL.toString());
        return precipitationMap.get(precipitationNum);
    }
    private String getSeasonRus(String season) {
        Map<String, String> seasonMap = new HashMap<>();
        seasonMap.put("summer", SeasonEnum.SUMMER.toString());
        seasonMap.put("autumn", SeasonEnum.AUTUMN.toString());
        seasonMap.put("winter", SeasonEnum.WINTER.toString());
        seasonMap.put("spring", SeasonEnum.SPRING.toString());
        return seasonMap.get(season);
    }
}
