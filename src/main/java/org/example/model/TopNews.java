package org.example.model;

public class TopNews {
    private String guid;
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String image;
    private String sourceId;

    public TopNews() {
    }

    public TopNews(String guid, String title, String link, String description, String pubDate, String image, String sourceID) {
        this.guid = guid;
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.image = image;
        this.sourceId = sourceID;
    }

    public String getArticleId() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getImage() {
        return image;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void printInfo() {
        System.out.println("Guid: " + guid);
        System.out.println("Title: " + title);
        System.out.println("Link: " + link);
        System.out.println("Description: " + description);
        System.out.println("PubDate: " + pubDate);
        System.out.println("Image: " + image);
        System.out.println("Source ID: " + sourceId);
        System.out.println("_______________________________________");
    }
}
