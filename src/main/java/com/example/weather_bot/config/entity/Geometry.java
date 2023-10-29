package com.example.weather_bot.config.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {
    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
