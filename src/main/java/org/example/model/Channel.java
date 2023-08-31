package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Channel {
    private String channelID;
    private String channelName;
    private String rssURL;
    private List<PropertyMap> propertyMaps;
    private List<String> imgTagList;
    private String imgAttr;

    @JsonProperty("channelID")
    public String getChannelID() { return channelID; }
    @JsonProperty("channelID")
    public void setChannelID(String value) { this.channelID = value; }

    @JsonProperty("channelName")
    public String getChannelName() { return channelName; }
    @JsonProperty("channelName")
    public void setChannelName(String value) { this.channelName = value; }

    @JsonProperty("rssURL")
    public String getRSSURL() { return rssURL; }
    @JsonProperty("rssURL")
    public void setRSSURL(String value) { this.rssURL = value; }
    @JsonProperty("mappings")
    public List<PropertyMap> getPropertyMaps() { return propertyMaps; }
    @JsonProperty("mappings")
    public void setPropertyMaps(List<PropertyMap> value) { this.propertyMaps = value; }

    @JsonProperty("imgTagList")
    public List<String> getImgTagList() { return imgTagList; }
    @JsonProperty("imgTagList")
    public void setImgTagList(List<String> value) { this.imgTagList = value; }

    @JsonProperty("imgAttr")
    public String getImgAttr() { return imgAttr; }
    @JsonProperty("imgAttr")
    public void setImgAttr(String value) { this.imgAttr = value; }
}
