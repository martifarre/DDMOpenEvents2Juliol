package com.example.ddmopenevents2.business;

import java.io.Serializable;

public class UserRegister implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String image;

    public UserRegister(String name, String surname, String email, String password, String image) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public UserRegister() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
