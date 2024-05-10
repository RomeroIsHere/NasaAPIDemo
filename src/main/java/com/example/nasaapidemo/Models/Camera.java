package com.example.nasaapidemo.Models;

public class Camera {

    private int id;
    private String nombre, fullName;

    public Camera() {
    }

    public Camera(int id, String nombre, String fullName) {
        this.id = id;
        this.nombre = nombre;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
