package com.example.weather_bot.entity;

public class PrecTypeEnum {
    private String title;

    private PrecTypeEnum(String title) {
        this.title = title;
    }
    public static PrecTypeEnum NO_PRECIPITATION = new PrecTypeEnum("Без осадков");
    public static PrecTypeEnum RAIN = new PrecTypeEnum("Дождь");
    public static PrecTypeEnum RAIN_AND_SNOW = new PrecTypeEnum("Дождь со снегом");
    public static PrecTypeEnum SNOW = new PrecTypeEnum("Снег");
    public static PrecTypeEnum HAIL = new PrecTypeEnum("Град");

    @Override
    public String toString() {
        return title;
    }
}
