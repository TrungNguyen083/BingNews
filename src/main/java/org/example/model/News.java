package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
    private String guid;
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String image;
    private Category category;

    public News() {
    }

    public News(String guid, String title, String description, String link, String pubDate, String image) {
        this.guid = guid;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }

    public void printOutInfo() {
        System.out.println();
        System.out.println("Guid: " + this.guid);
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("PubDate: " + this.pubDate);
        System.out.println("Link: " + this.link);
        System.out.println("Image: " + this.image);
    }

    @JsonProperty("category")
    public Category getCategories() { return category; }
    @JsonProperty("category")
    public void setCategories(Category value) { this.category = value; }

    @JsonProperty("title")
    public String getTitle() { return title; }
    @JsonProperty("title")
    public void setTitle(String value) { this.title = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("pubDate")
    public String getPubDate() { return pubDate; }
    @JsonProperty("pubDate")
    public void setPubDate(String value) { this.pubDate = value; }

    @JsonProperty("link")
    public String getLink() { return link; }
    @JsonProperty("link")
    public void setLink(String value) { this.link = value; }

    @JsonProperty("guid")
    public String getGUID() { return guid; }
    @JsonProperty("guid")
    public void setGUID(String value) { this.guid = value; }

    @JsonProperty("image")
    public String getImage() { return image; }
    @JsonProperty("image")
    public void setImage(String value) { this.image = value; }
}
