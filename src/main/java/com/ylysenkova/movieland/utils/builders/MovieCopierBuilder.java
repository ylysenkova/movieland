package com.ylysenkova.movieland.utils.builders;


import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieCopierBuilder {

    private Movie movie;
    private Movie movieCopy;
    private List<Country> countryList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    public static MovieCopierBuilder fromMovie(Movie movieCopy) {
        MovieCopierBuilder movieBuilder = new MovieCopierBuilder();
        movieBuilder.movieCopy = movieCopy;
        return movieBuilder;
    }


    public MovieCopierBuilder getMovie() {
        movie = new Movie();
        movie.setId(movieCopy.getId());
        movie.setNameRussian(movieCopy.getNameRussian());
        movie.setNameNative(movieCopy.getNameNative());
        movie.setYearOfRelease(movieCopy.getYearOfRelease());
        movie.setRating(movieCopy.getRating());
        movie.setPrice(movieCopy.getPrice());
        movie.setDescription(movieCopy.getDescription());
        if (movie.getGenres() != null) {
            for (Genre genre : this.movieCopy.getGenres()) {
                this.genreList.add(genre);
            }
            movie.setGenres(genreList);
        }
        if (movie.getCountries() != null) {
            for (Country country : this.movieCopy.getCountries()) {
                this.countryList.add(country);
            }
            movie.setCountries(countryList);
        }
        movie.setPicturePath(movieCopy.getPicturePath());
        return this;
    }


    public Movie build() {
        return movie;
    }

}
