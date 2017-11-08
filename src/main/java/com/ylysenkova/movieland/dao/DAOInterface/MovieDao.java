package com.ylysenkova.movieland.dao.DAOInterface;

import com.ylysenkova.movieland.model.Movie;

import java.util.List;
import java.util.Set;

public interface MovieDao {

    public  List<Movie> getAllMovies();

    public Set<Integer> getThreeMovieIds();

    public List<Movie> getThreeMovies(Set<Integer> movieIds);

    public List<Movie> getMovieByGenreId(int genreId);

    public List<Movie> getSortingByRating(String sortByRating);

    public List<Movie> getSortingByPrice(String sortByPrice);
}
