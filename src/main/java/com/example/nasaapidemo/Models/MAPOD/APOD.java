package com.example.nasaapidemo.Models.MAPOD;

public class APOD {

    private int id;

    private MediaType cveMedia;

    private String date, title;

    private String explanation, url, hdurl, thumbnailurl, serviceVersion;

    public APOD() {
    }

    public APOD(int id, MediaType cveMedia, String date, String title, String explanation, String url, String hdUrl, String thumbnailUrl, String serviceVersion) {
        this.id = id;
        this.cveMedia = cveMedia;
        this.date = date;
        this.title = title;
        this.explanation = explanation;
        this.url = url;
        this.hdurl = hdUrl;
        this.thumbnailurl = thumbnailUrl;
        this.serviceVersion = serviceVersion;
    }

    @Override
    public String toString() {

        return id +
                date +
                title +
                explanation +
                url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MediaType getCveMedia() {
        return cveMedia;
    }

    public void setCveMedia(MediaType cveMedia) {
        this.cveMedia = cveMedia;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdUrl() {
        return hdurl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdurl = hdUrl;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serveceVersion) {
        this.serviceVersion = serveceVersion;
    }
}
