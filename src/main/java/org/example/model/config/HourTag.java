package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HourTag {
    private String propertyName;
    private String extendURL;
    private String startTag;
    private String endTag;
    private List<HourTempConfig> hourTempConfig;

    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("extendUrl")
    public String getExtendURL() {
        return extendURL;
    }

    @JsonProperty("extendUrl")
    public void setExtendURL(String value) {
        this.extendURL = value;
    }

    @JsonProperty("startTag")
    public String getStartTag() {
        return startTag;
    }

    @JsonProperty("startTag")
    public void setStartTag(String value) {
        this.startTag = value;
    }

    @JsonProperty("endTag")
    public String getEndTag() {
        return endTag;
    }

    @JsonProperty("endTag")
    public void setEndTag(String value) {
        this.endTag = value;
    }

    @JsonProperty("hourTempConfig")
    public List<HourTempConfig> getListHourTemperature() {
        return hourTempConfig;
    }

    @JsonProperty("hourTempConfig")
    public void setListHourTemperature(List<HourTempConfig> value) {
        this.hourTempConfig = value;
    }
}
