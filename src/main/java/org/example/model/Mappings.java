package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mappings {
    private String title;
    private String enclosureURL;
    private String link;
    private String pubDate;
    private String guid;
    private String description;
    private String descriptionImgScr;

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("enclosure.url")
    public String getEnclosureURL() { return enclosureURL; }
    @JsonProperty("enclosure.url")
    public void setEnclosureURL(String value) { this.enclosureURL = value; }

    @JsonProperty("link")
    public String getLink() { return link; }
    @JsonProperty("link")
    public void setLink(String value) { this.link = value; }

    @JsonProperty("pubDate")
    public String getPubDate() { return pubDate; }
    @JsonProperty("pubDate")
    public void setPubDate(String value) { this.pubDate = value; }

    @JsonProperty("guid")
    public String getGUID() { return guid; }
    @JsonProperty("guid")
    public void setGUID(String value) { this.guid = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("description.img#scr")
    public String getDescriptionImgScr() { return descriptionImgScr; }
    @JsonProperty("description.img#scr")
    public void setDescriptionImgScr(String value) { this.descriptionImgScr = value; }
}
