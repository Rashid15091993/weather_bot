package com.example.weather_bot.entity;


public class SeasonEnum {
    private String season;
    private SeasonEnum(String season) {
        this.season = season;
    }
    public static SeasonEnum SUMMER = new SeasonEnum("Лето");
    public static SeasonEnum AUTUMN = new SeasonEnum("Осень");
    public static SeasonEnum WINTER = new SeasonEnum("Зима");
    public static SeasonEnum SPRING = new SeasonEnum("Весна");

    @Override
    public String toString() {
        return season;
    }
}
