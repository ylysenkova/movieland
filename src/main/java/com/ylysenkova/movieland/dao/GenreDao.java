package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    void enrichMovieWithGenres(List<Movie> movieList);
}
