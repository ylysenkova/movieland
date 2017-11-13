package com.ylysenkova.movieland.dao;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;

import java.util.List;
import java.util.Set;

public interface MovieDao {

    public List<Movie> getAll();

    public Set<Integer> getThreeMovieIds();

    public List<Movie> getThreeMovies(Set<Integer> movieIds);

    public List<Movie> getMovieByGenreId(int genreId);

    public List<Movie> getAllMoviesSorted(String field, Sorting direction);

    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction);

}
