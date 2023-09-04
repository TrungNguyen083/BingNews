package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyMap {
    private String propertyName;
    private String tagName;

    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("tagName")
    public String getTagName() { return tagName; }
    @JsonProperty("tagName")
    public void setTagName(String value) { this.tagName = value; }
}
