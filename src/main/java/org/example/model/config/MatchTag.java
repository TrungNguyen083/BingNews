package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MatchTag {
    private String propertyName;
    private List<PropertyMap> matchMappings;

    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("matchMappings")
    public List<PropertyMap> getMatchMappings() { return matchMappings; }
    @JsonProperty("matchMappings")
    public void setMatchMappings(List<PropertyMap> value) { this.matchMappings = value; }
}
