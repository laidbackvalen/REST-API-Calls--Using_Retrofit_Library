package com.example.retrofitputlearning.models;

public class DataModel {
    // in the below line, we are creating
    // variables for email and name, id
    String email;
    String name;
    long id;

    public DataModel() {
    }

    ;

    public DataModel(String email, String name, long id) {
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
