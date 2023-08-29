package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Channel> listChanel;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("listChanel")
    public List<Channel> getListChanel() { return listChanel; }
    @JsonProperty("listChanel")
    public void setListChanel(List<Channel> value) { this.listChanel = value; }
}
