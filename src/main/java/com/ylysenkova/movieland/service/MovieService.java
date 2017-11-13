package com.ylysenkova.movieland.service;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;

import java.util.List;

public interface MovieService {

    public List<Movie> getAll();

    public List<Movie> getThreeMovies();

    public List<Movie> getMovieByGenreId(int genreId);

    public List<Movie> getAllSorted(String field, Sorting direction);

}
