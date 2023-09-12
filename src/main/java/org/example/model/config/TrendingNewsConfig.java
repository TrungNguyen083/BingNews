package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrendingNewsConfig {
    private List<Channel> listChanel;

    @JsonProperty("listChanel")
    public List<Channel> getListChanel() { return listChanel; }
    @JsonProperty("listChanel")
    public void setListChanel(List<Channel> value) { this.listChanel = value; }
}
