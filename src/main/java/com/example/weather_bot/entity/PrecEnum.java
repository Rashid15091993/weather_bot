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
    public static PrecEnum LIGHT_RAIN_SNOW = new PrecEnum("Слабый дождь со снегом");
    public static PrecEnum RAIN_SNOW = new PrecEnum("Дождь со снегом");
    public static PrecEnum HEAVY_RAIN_SNOW = new PrecEnum("Сильный дождь со снегом");
    public static PrecEnum VERY_HEAVY_RAIN_SNOW = new PrecEnum("Очень сильный дождь со снегом");

    public static PrecEnum LIGHT_HAIL = new PrecEnum("Слабый град");
    public static PrecEnum HAIL = new PrecEnum("Град");
    public static PrecEnum HEAVY_HAIL = new PrecEnum("Сильный град");
    public static PrecEnum VERY_HEAVY_HAIL = new PrecEnum("Очень сильный град");

    @Override
    public String toString() {
        return title;
    }
}
