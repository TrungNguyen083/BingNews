package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherConfig {
    private List<WeatherChannel> weatherChannels;

    @JsonProperty("weatherChannels")
    public List<WeatherChannel> getWeatherChannels() { return weatherChannels; }
    @JsonProperty("weatherChannels")
    public void setWeatherChannels(List<WeatherChannel> value) { this.weatherChannels = value; }
}
