package com.jroomstudio.jsoupsample.jsoupsample2;

public class ItemObject {

    private String title;
    private String img_url;
    private String detail_ilnk;
    private String release;
    private String director;

    public ItemObject(String title, String url, String link, String release, String director){
        this.title = title;
        this.img_url = url;
        this.detail_ilnk = link;
        this.release = release;
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDetail_ilnk() {
        return detail_ilnk;
    }

    public void setDetail_ilnk(String detail_ilnk) {
        this.detail_ilnk = detail_ilnk;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
