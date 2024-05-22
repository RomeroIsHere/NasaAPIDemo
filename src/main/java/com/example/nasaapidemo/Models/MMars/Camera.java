package com.example.nasaapidemo.Models.MMars;

import com.google.gson.annotations.SerializedName;

public class Camera {

    private int id;
    private int rover_id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;

    public Camera() {
    }

    public Camera(int id, String nombre, String fullName) {
        this.id = id;
        this.name = nombre;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRover_id() {
        return rover_id;
    }

    public void setRover_id(int rover_id) {
        this.rover_id = rover_id;
    }
}
