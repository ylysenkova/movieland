package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface CountryDao {

    void enrichMovieWithCountries(List<Movie> movieList);
}
