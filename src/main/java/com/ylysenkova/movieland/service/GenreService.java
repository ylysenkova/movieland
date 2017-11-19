package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    void enrichMovieWithGenres(List<Movie> movieList);
}
