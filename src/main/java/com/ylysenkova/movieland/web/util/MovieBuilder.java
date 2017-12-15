package com.ylysenkova.movieland.web.util;


import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.web.dto.request.SaveMovieRequest;

import java.util.ArrayList;
import java.util.List;

public class MovieBuilder {

    private SaveMovieRequest saveMovieRequest;
    private Movie movie;
    private List<Country> countryList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    public static MovieBuilder fromMovieRequest(SaveMovieRequest saveMovieRequest) {
        MovieBuilder movieBuilder = new MovieBuilder();
        movieBuilder.saveMovieRequest = saveMovieRequest;
        return movieBuilder;
    }


    public MovieBuilder getMovie() {

        movie = new Movie();
        movie.setId(saveMovieRequest.getId());
        movie.setNameRussian(saveMovieRequest.getNameRussian());
        movie.setNameNative(saveMovieRequest.getNameNative());
        movie.setYearOfRelease(saveMovieRequest.getYearOfRelease());
        movie.setRating(saveMovieRequest.getRating());
        movie.setPrice(saveMovieRequest.getPrice());
        movie.setDescription(saveMovieRequest.getDescription());
        if (movie.getGenres() == null) {
            return this;
        }
        for (Integer genreId : this.saveMovieRequest.getGenres()) {
            this.genreList.add(new Genre(genreId));
        }
        movie.setGenres(genreList);
        if (movie.getCountries() == null) {
            return this;
        }
        for (Integer countryId : this.saveMovieRequest.getCountries()) {
            this.countryList.add(new Country(countryId));
        }
        movie.setCountries(countryList);
        movie.setPicturePath(saveMovieRequest.getPicturePath());
        return this;
    }


    public Movie build() {
        return movie;
    }

}
