package com.example.nasaapidemo.Models;

public class MediaType {

    private int cveMedia;
    private String name;

    public MediaType() {
    }

    public MediaType(int cveMedia, String name) {
        this.cveMedia = cveMedia;
        this.name = name;
    }

    public int getCveMedia() {
        return cveMedia;
    }

    public void setCveMedia(int cveMedia) {
        this.cveMedia = cveMedia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
