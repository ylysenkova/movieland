package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface CountryService {

    void enrichMovieWithCountries (List<Movie> movieList);
}
