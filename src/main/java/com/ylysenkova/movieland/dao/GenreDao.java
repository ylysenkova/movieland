package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    void enrichMoviesWithGenres(List<Movie> movieList);

    void enrichMovieWithGenres(Movie movie);

    void removeGenreMovieLink(Movie movie);

    void editAddGenre(Movie movie);

}
