package com.ylysenkova.model;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class Country {

    private int countryId;
    private String nameCountry;

    public Country() {
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", nameCountry='" + nameCountry + '\'' +
                '}';
    }
}
