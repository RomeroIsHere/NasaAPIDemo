package com.example.nasaapidemo.Models;

public class Rover {

    private int idRover, totalPhotos, maxSol;
    private Status idStatus;
    private String name, landingDate, launchDate, maxDate;

    public Rover() {
    }

    public Rover(int idRover, int totalPhotos, int maxSol, Status idStatus, String name, String landingDate, String launchDate, String maxDate) {
        this.idRover = idRover;
        this.totalPhotos = totalPhotos;
        this.maxSol = maxSol;
        this.idStatus = idStatus;
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.maxDate = maxDate;
    }

    public int getIdRover() {
        return idRover;
    }

    public void setIdRover(int idRover) {
        this.idRover = idRover;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public int getMaxSol() {
        return maxSol;
    }

    public void setMaxSol(int maxSol) {
        this.maxSol = maxSol;
    }

    public Status getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Status idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }
}
