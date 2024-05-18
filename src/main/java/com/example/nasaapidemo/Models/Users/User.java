package com.example.nasaapidemo.Models.Users;

public class User {
    String gmail, user, password;
    int admin;

    public User(){

    }
    public User(String gmail, String user, String password, int admin) {
        this.gmail = gmail;
        this.user = user;
        this.password = password;
        this.admin = admin;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
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

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
