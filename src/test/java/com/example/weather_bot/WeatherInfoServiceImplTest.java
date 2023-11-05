package com.example.weather_bot;


import com.example.weather_bot.entity.dto.WeatherDto;
import com.example.weather_bot.service.WeatherInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherInfoServiceImplTest {
    @Autowired
    WeatherInfoServiceImpl weatherInfoService;
    private WeatherDto weatherDto = new WeatherDto();
    private String info;
    private Logger logger = LoggerFactory.getLogger(WeatherInfoServiceImplTest.class);

    @BeforeEach
    public void init(){

        info = "Населенный пункт -> Москва\n" +
                "Температура сейчас -> 16\n" +
                "Тип осадков -> Дождь со снегом\n" +
                "Сила осадков -> Очень сильный дождь со снегом\n" +
                "Время года -> Весна\n";
        weatherDto.setCity("Москва");
        weatherDto.setTempFact("16");
        weatherDto.setPrecStrength("1");
        weatherDto.setSeason("spring");
        weatherDto.setPrecType("2");
    }

    @Test
    public void testGetWeatherCityCache() {

        String result1 = weatherInfoService.getWeatherInfo(weatherDto);
        String result2 = weatherInfoService.getWeatherInfo(weatherDto);
        loggInfo(result1);
        loggInfo(result2);
        assertEquals(info, result1);
    }
    private void loggInfo(String result) {
        logger.info("Cache info {}", result);
    }
}
