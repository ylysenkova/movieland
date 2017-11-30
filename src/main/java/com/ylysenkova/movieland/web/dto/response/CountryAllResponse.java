package com.ylysenkova.movieland.web.dto.response;


import com.ylysenkova.movieland.model.Country;

public class CountryAllResponse {
    private int id;
    private String name;

    public CountryAllResponse() {
    }

    public CountryAllResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
