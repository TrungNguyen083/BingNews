package org.example.model;

import org.example.ORM.TNframwork.Column;
import org.example.ORM.TNframwork.Entity;

@Entity(name = "adarticle")
public class AdArticle {
    @Column(name = "guid")
    private String guid;

    @Column(name = "adImage")
    private String adImage;

    @Column(name = "adTitle")
    private String adTitle;

    @Column(name = "adSource")
    private String adSource;

    @Column(name = "adLink")
    private String adLink;

    public AdArticle() {
    }

    public AdArticle(String adImage, String adTitle, String adSource, String adLink) {
        this.adImage = adImage;
        this.adTitle = adTitle;
        this.adSource = adSource;
        this.adLink = adLink;
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
