package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HourTag {

    private String propertyName;
    private String tag;
    private List<PropertyMap> hourMappings;


    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("tag")
    public String getTag() { return tag; }
    @JsonProperty("tag")
    public void setTag(String value) { this.tag = value; }

    @JsonProperty("hourMappings")
    public List<PropertyMap> getHourMappings() { return hourMappings; }
    @JsonProperty("hourMappings")
    public void setHourMappings(List<PropertyMap> value) { this.hourMappings = value; }
}
