package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherConfig implements APIConfig{
    private String method;
    private String uri;
    private String keyName;
    private String keyValue;
    private String hostName;
    private String hostValue;
    private String forecastTag;
    private List<PropertyMap> weatherMappings;
    private HourTag hourTag;

    @JsonProperty("method")
    public String getMethod() { return method; }
    @JsonProperty("method")
    public void setMethod(String value) { this.method = value; }

    @JsonProperty("uri")
    public String getURI() { return uri; }
    @JsonProperty("uri")
    public void setURI(String value) { this.uri = value; }

    @JsonProperty("keyName")
    public String getKeyName() { return keyName; }
    @JsonProperty("keyName")
    public void setKeyName(String value) { this.keyName = value; }

    @JsonProperty("keyValue")
    public String getKeyValue() { return keyValue; }
    @JsonProperty("keyValue")
    public void setKeyValue(String value) { this.keyValue = value; }

    @JsonProperty("hostName")
    public String getHostName() { return hostName; }
    @JsonProperty("hostName")
    public void setHostName(String value) { this.hostName = value; }

    @JsonProperty("hostValue")
    public String getHostValue() { return hostValue; }
    @JsonProperty("hostValue")
    public void setHostValue(String value) { this.hostValue = value; }

    @JsonProperty("forecastTag")
    public String getForecastTag() { return forecastTag; }
    @JsonProperty("forecastTag")
    public void setForecastTag(String value) { this.forecastTag = value; }

    @JsonProperty("weatherMappings")
    public List<PropertyMap> getWeatherMappings() { return weatherMappings; }
    @JsonProperty("weatherMappings")
    public void setWeatherMappings(List<PropertyMap> value) { this.weatherMappings = value; }

    @JsonProperty("hourTag")
    public HourTag getHourTag() { return hourTag; }
    @JsonProperty("hourTag")
    public void setHourTag(HourTag value) { this.hourTag = value; }

}
