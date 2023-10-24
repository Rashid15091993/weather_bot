package com.example.weather_bot.config.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    /**
     * user's id
     */
    @JsonProperty("id")
    private final int id;
    /**
     * user's name
     */
    @JsonProperty("name")
    private final String name;
    /**
     * description
     */
    @JsonProperty("description")
    private final String description;

    private String startWord = "";

    @Override
    public String toString() { return startWord + description; }
}
