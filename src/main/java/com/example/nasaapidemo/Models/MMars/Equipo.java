package com.example.nasaapidemo.Models.MMars;

public class Equipo {

    private Camera idCamera;
    private Rover idRover;

    public Equipo() {
    }

    public Equipo(Camera idCamera, Rover idRover) {
        this.idCamera = idCamera;
        this.idRover = idRover;
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
}
