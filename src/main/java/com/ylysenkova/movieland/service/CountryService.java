package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface CountryService {

    void enrichMoviesWithCountries (List<Movie> movieList);

    void enrichMovieWithCountries (Movie movie);
}