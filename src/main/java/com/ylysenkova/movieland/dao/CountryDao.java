package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface CountryDao {

    void enrichMoviesWithCountries(List<Movie> movieList);

    void enrichMovieWithCountries(Movie movie);

    List<Country> getAll();

    void removeCountryMovieLink(Movie movie);

    void editAddCountry(Movie movie);
}
