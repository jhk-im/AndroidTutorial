package com.empty.samplenewsapp;

import java.io.Serializable;

// Serializable 을 상속 받으면
// Intent 에서 값을 넘길 때 class 자체를 넘길 수 있다.

public class NewsData implements Serializable { // Serializable -> 직렬화

    // 각각 title , urlToImage, description 을 셋팅한다.
    private  String title;
    private  String urlToImage;
    private  String description;

    // 입력과 호출은 getter setter 를 사용한다. 
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
