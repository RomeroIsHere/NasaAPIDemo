package com.example.nasaapidemo.Models;

public class Photos {

    private int id, sol;
    private Camera idCamera;
    private Rover idRover;
    private String imageSrc, earthDate;

    public Photos() {
    }

    public Photos(int id, int sol, Camera idCamera, Rover idRover, String imageSrc, String earthDate) {
        this.id = id;
        this.sol = sol;
        this.idCamera = idCamera;
        this.idRover = idRover;
        this.imageSrc = imageSrc;
        this.earthDate = earthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public Camera getIdCamera() {
        return idCamera;
    }

    public void setIdCamera(Camera idCamera) {
        this.idCamera = idCamera;
    }

    public Rover getIdRover() {
        return idRover;
    }

    public void setIdRover(Rover idRover) {
        this.idRover = idRover;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }
}
