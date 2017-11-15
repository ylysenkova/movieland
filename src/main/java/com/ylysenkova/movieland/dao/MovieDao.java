package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;

import java.util.List;
import java.util.Set;

public interface MovieDao {

    List<Movie> getAll();

    Set<Integer> getThreeMovieIds();

    List<Movie> getThreeMovies(Set<Integer> movieIds);

    List<Movie> getMovieByGenreId(int genreId);

    List<Movie> getAllMoviesSorted(String field, Sorting direction);

    List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction);

}
