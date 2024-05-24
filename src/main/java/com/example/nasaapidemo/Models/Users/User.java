package com.example.nasaapidemo.Models.Users;

public class User {
    String user, password;
    TipoUser cveTipoUser;


    public User(){

    }
    public User(TipoUser cveTipoUser, String user, String password) {
        this.cveTipoUser = cveTipoUser;
        this.user = user;
        this.password = password;

    }

    public TipoUser getCveTipoUser() {
        return cveTipoUser;
    }

    public void setCveTipoUser(TipoUser cveTipoUser) {
        this.cveTipoUser = cveTipoUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
