package com.example.appexcerse.model;

public class Catagoty {
    private  long id;
    private String name;

    public Catagoty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
