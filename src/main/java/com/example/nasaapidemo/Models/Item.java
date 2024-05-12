package com.example.nasaapidemo.Models;

public class Item {

    private int idNasa;

    private MediaType cveMedia;

    private String href, center, title, photographer, location, dateCreation;

    public Item() {
    }

    public Item(int idNasa, MediaType cveMedia, String href, String center, String title, String photographer, String location, String dateCreation) {
        this.idNasa = idNasa;
        this.cveMedia = cveMedia;
        this.href = href;
        this.center = center;
        this.title = title;
        this.photographer = photographer;
        this.location = location;
        this.dateCreation = dateCreation;
    }

    public int getIdNasa() {
        return idNasa;
    }

    public void setIdNasa(int idNasa) {
        this.idNasa = idNasa;
    }

    public MediaType getCveMedia() {
        return cveMedia;
    }

    public void setCveMedia(MediaType cveMedia) {
        this.cveMedia = cveMedia;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}