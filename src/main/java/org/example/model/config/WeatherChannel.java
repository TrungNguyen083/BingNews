package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherChannel {
    private String apiName;
    private String apiUrl;
    private List<WeatherMap> weatherMapList;
    private HourTag hourTag;

    @JsonProperty("apiName")
    public String getAPIName() {
        return apiName;
    }

    @JsonProperty("apiName")
    public void setAPIName(String value) {
        this.apiName = value;
    }

    @JsonProperty("apiUrl")
    public String getAPIUrl() {
        return apiUrl;
    }

    @JsonProperty("apiUrl")
    public void setAPIUrl(String value) {
        this.apiUrl = value;
    }

    @JsonProperty("mappings")
    public List<WeatherMap> getMappings() {
        return weatherMapList;
    }

    @JsonProperty("mappings")
    public void setMappings(List<WeatherMap> value) {
        this.weatherMapList = value;
    }

    @JsonProperty("hourTag")
    public HourTag getHourTag() { return hourTag; }
    @JsonProperty("hourTag")
    public void setHourTag(HourTag value) { this.hourTag = value; }


}
