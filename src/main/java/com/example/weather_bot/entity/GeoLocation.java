package com.example.weather_bot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocation {
    @JsonProperty("results")
    private Result[] results;

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }
}
