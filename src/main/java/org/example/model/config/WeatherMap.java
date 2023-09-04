package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherMap {
    private String propertyName;
    private String startTag;
    private String endTag;

    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("startTag")
    public String getStartTag() { return startTag; }
    @JsonProperty("startTag")
    public void setStartTag(String value) { this.startTag = value; }

    @JsonProperty("endTag")
    public String getEndTag() { return endTag; }
    @JsonProperty("endTag")
    public void setEndTag(String value) { this.endTag = value; }
}
