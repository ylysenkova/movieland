package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import com.ylysenkova.movieland.model.User;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getThreeMovies();

    List<Movie> getMovieByGenreId(int genreId);

    Movie getMovieById(int movieId);

    List<Movie> getAllSorted(String field, Sorting direction);

    List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction);

    void addMovie(Movie movie);

    void editMovie(Movie movie);

    void rateMovie(int movieId, double rating, User user);
}
