package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Country;

import java.util.List;

public interface CountryDao {

    public List<Country> getCountryByMovieId();
}
