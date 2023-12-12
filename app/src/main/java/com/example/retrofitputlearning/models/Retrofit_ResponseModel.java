package com.example.retrofitputlearning.models;

public class Retrofit_ResponseModel {

    String name;
    String email;

    public Retrofit_ResponseModel(){}
    public Retrofit_ResponseModel(String name, String email) {
        this.name = name;
        this.email = email;
    }


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
}
