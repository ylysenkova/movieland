package com.ylysenkova.movieland.service.serviceInterface;

import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface MovieServiceInterface {

    public List<Movie> getAllMovies();

    public List<Movie> getThreeMovies();
}
