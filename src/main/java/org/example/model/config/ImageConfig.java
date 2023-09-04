package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ImageConfig {
    private List<Channel> channels;

    @JsonProperty("channels")
    public List<Channel> getChannels() { return channels; }
    @JsonProperty("channels")
    public void setChannels(List<Channel> value) { this.channels = value; }
}
