package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TopNewsConfig implements APIConfig{
    private String method;
    private String uri;
    private String keyName;
    private String keyValue;
    private String hostName;
    private String hostValue;
    private String itemTag;
    private List<PropertyMap> topNewsMappings;

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

    @JsonProperty("itemTag")
    public String getItemTag() { return itemTag; }
    @JsonProperty("itemTag")
    public void setItemTag(String value) { this.itemTag = value; }

    @JsonProperty("topNewsMappings")
    public List<PropertyMap> getTopNewsMappings() { return topNewsMappings; }
    @JsonProperty("topNewsMappings")
    public void setTopNewsMappings(List<PropertyMap> value) { this.topNewsMappings = value; }
}
