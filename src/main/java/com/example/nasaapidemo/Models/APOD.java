package com.example.nasaapidemo.Models;

import java.util.Date;

public class APOD {

    private int id;

    private MediaType cveMedia;

    private Date date;

    private String explanation, url, hdUrl, thumbnailUrl, serviceVersion;

    public APOD() {
    }

    public APOD(int id, MediaType cveMedia, Date date, String explanation, String url, String hdUrl, String thumbnailUrl, String serviceVersion) {
        this.id = id;
        this.cveMedia = cveMedia;
        this.date = date;
        this.explanation = explanation;
        this.url = url;
        this.hdUrl = hdUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.serviceVersion = serviceVersion;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serveceVersion) {
        this.serviceVersion = serveceVersion;
    }
}
