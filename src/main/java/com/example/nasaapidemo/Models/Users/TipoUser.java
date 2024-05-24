package com.example.nasaapidemo.Models.Users;

public class TipoUser {
    String cveTipoUser, description;

    public TipoUser (){

    }

    public TipoUser(String cveTipoUser, String description) {
        this.cveTipoUser = cveTipoUser;
        this.description = description;
    }

    public String getCveTipoUser() {
        return cveTipoUser;
    }

    public void setCveTipoUser(String cveTipoUser) {
        this.cveTipoUser = cveTipoUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
