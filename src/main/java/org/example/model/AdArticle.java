package org.example.model;

public class AdArticle {
    private String guid;
    private String adImage;
    private String adTitle;
    private String adSource;
    private String adLink;

    public AdArticle() {
    }

    public AdArticle(String guid, String adImage, String adTitle, String adSource, String adLink) {
        this.guid = guid;
        this.adImage = adImage;
        this.adTitle = adTitle;
        this.adSource = adSource;
        this.adLink = adLink;
    }

    public String getGuid() {
        return guid;
    }

    public String getAdImage() {
        return adImage;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdSource() {
        return adSource;
    }

    public String getAdLink() {
        return adLink;
    }

    public void printInfo() {
        System.out.println("guid: " + guid);
        System.out.println("adImage: " + adImage);
        System.out.println("adTitle: " + adTitle);
        System.out.println("adSource: " + adSource);
        System.out.println("adLink: " + adLink);
        System.out.println("__________________________________");
    }
}
