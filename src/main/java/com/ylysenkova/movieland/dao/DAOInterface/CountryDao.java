package com.ylysenkova.movieland.dao.DAOInterface;

import com.ylysenkova.movieland.model.Country;

import java.util.List;

public interface CountryDao {

public List<Country> getCountryByMovieId();
}
