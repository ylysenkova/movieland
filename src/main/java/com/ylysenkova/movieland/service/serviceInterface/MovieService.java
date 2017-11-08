package com.ylysenkova.movieland.service.serviceInterface;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;

import java.util.List;

public interface MovieService {

    public List<Movie> getAllMovies();

    public List<Movie> getThreeMovies();

    public List<Movie> getMovieByGenreId(int genreId);
}
