package com.ylysenkova.movieland.service.serviceInterface;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface MovieService {

    public List<Movie> getAll();

    public List<Movie> getThreeMovies();

    public List<Movie> getMovieByGenreId(int genreId);

    public List<Movie> getSortingByRating(String sortRating);

    public List<Movie> getSortingByPrice(String sortPrice);
}
