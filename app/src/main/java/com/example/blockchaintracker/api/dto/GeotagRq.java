package com.example.blockchaintracker.api.dto;

public class GeotagRq {

    private String position;

    public GeotagRq(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}