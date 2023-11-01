package com.example.weather_bot;


import com.example.weather_bot.entity.dto.WeatherDto;
import com.example.weather_bot.service.WeatherJsonService;
import com.example.weather_bot.service.WeatherJsonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherJsonServiceImplTest {
    @Autowired
    WeatherJsonService weatherJsonService;
    /*
    @Test
    public void testGetWeatherCity() {
        WeatherDto resultCache = weatherJsonService.createWeatherDto("test");
    }

     */

}
