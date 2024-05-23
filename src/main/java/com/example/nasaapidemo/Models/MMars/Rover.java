package com.example.nasaapidemo.Models.MMars;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Rover {
    @SerializedName("id")
    private int idRover;
    @SerializedName("total_photos")
    private int totalPhotos;
    @SerializedName("max_sol")
    private int maxSol;

    private String status;
    private String max_date;
    private String landing_date,launch_date;
    private String name;
    //Following need to use updateByJson when Yielding to GSON creation
    private Date landingDate, launchDate, maxDate;

    private Status idStatus;
    @SerializedName("cameras")
    Camera[] cameras;

    public Rover() {
    }

    public Rover(int idRover, int totalPhotos, int maxSol, Status idStatus, String name, Date landingDate, Date launchDate, Date maxDate) {
        this.idRover = idRover;
        this.totalPhotos = totalPhotos;
        this.maxSol = maxSol;
        this.idStatus = idStatus;
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.maxDate = maxDate;
    }

    public boolean updateByJson(){
        try{
            maxDate=new Date(max_date);
            launchDate=new Date(launch_date);
            landingDate=new Date(landing_date);

            idRover=getIdBYRoverName(name);
            idStatus=new Status();
            idStatus.setStatus(status);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private int getIdBYRoverName(String name) {
        int returnable=switch(name.toLowerCase()){
            case "curiosity":
                yield 5;
            case "opportunity":
                yield 6;
            case "spirit":
                yield 5;
            default:
                yield -1;
        };
        return returnable;
    }

    public String getStatus() {
        return status;
    }

    public Camera[] getCameras() {
        return cameras;
    }

    public void setCameras(Camera[] cameras) {
        this.cameras = cameras;
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

    public Date getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(Date landingDate) {
        this.landingDate = landingDate;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
