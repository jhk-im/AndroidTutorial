package com.empty.samplenewsapp;

import java.io.Serializable;

public class NewsData implements Serializable { // Serializable -> 직렬화

    private  String title;
    private  String urlToImage;
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
