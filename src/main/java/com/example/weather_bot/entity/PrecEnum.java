package com.example.weather_bot.entity;

public class PrecEnum {
    private String title;

    private PrecEnum(String title) {
        this.title = title;
    }
    public static PrecEnum NO_PRECIPITATION = new PrecEnum("Без осадков");
    public static PrecEnum LIGHT_RAIN = new PrecEnum("Слабый дождь");
    public static PrecEnum LIGHT_SNOW = new PrecEnum("Слабый снег");
    public static PrecEnum RAIN = new PrecEnum("Дождь");
    public static PrecEnum SNOW = new PrecEnum("Снег");
    public static PrecEnum HEAVY_RAIN = new PrecEnum("Сильный дождь");
    public static PrecEnum HEAVY_SNOW = new PrecEnum("Сильный снег");
    public static PrecEnum HEAVY_DOWNPOUR = new PrecEnum("Сильный ливень");
    public static PrecEnum VERY_HEAVY_SNOW = new PrecEnum("Очень сильный снег");

    @Override
    public String toString() {
        return title;
    }
}
