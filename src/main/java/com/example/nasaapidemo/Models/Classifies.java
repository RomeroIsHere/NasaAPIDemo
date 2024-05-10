package com.example.nasaapidemo.Models;

public class Classifies {

    private Item idNasa;

    private Keywords name;

    public Classifies() {
    }

    public Classifies(Item idNasa, Keywords name) {
        this.idNasa = idNasa;
        this.name = name;
    }

    public Item getIdNasa() {
        return idNasa;
    }

    public void setIdNasa(Item idNasa) {
        this.idNasa = idNasa;
    }

    public Keywords getName() {
        return name;
    }

    public void setName(Keywords name) {
        this.name = name;
    }
}
