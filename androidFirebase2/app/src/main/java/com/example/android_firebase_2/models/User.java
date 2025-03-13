package com.example.android_firebase_2.models;

import java.util.ArrayList;

/**
 * Lo modifico por el guardado del usuario en Shared Preferences:
 * el usuario debe tener el campo uid, que hasta ahora no tenía
 */
public class User {
    private String uid;   // tengo que añadir el uid del usuario
    public String name;
    public String email;
    public String phone;
    public String address;
    private ArrayList<String> favourites = new ArrayList<>();


    // necesario para firebase:
    public User() { }

    public User(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Añado entonces tb. el onstructor con uid:
    public User(String uid, String name, String email, String phone, String address) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // getters y setters:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<String> favourites) {
        this.favourites = favourites;
    }

    //getter & setter del uid

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

