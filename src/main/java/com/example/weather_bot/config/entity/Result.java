package com.example.weather_bot.config.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty("geometry")
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
