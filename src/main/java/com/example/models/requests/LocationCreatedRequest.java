package com.example.models.requests;

public class LocationCreatedRequest {

    private  Long id;

    private String name;

//    public LocationCreatedRequest(String name) {
//        this.name = name;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
