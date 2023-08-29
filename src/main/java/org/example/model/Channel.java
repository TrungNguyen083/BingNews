package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Channel {
    private String channelID;
    private String channelName;
    private String rssURL;
    private Mappings mappings;

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
    public Mappings getMappings() { return mappings; }
    @JsonProperty("mappings")
    public void setMappings(Mappings value) { this.mappings = value; }
}
