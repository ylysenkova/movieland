package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.jdbc.JdbcMovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.serviceInterface.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private JdbcMovieDao jdbcMovieDaoImpl;

    @Override
    public List<Movie> getAll() {
        return jdbcMovieDaoImpl.getAll();
    }

    @Override
    public List<Movie> getThreeMovies() {
        return jdbcMovieDaoImpl.getThreeMovies(jdbcMovieDaoImpl.getThreeMovieIds());
    }

    public List<Movie> getMovieByGenreId(int genreId) {
        return jdbcMovieDaoImpl.getMovieByGenreId(genreId);
    }

    public List<Movie> getSortingByRating(String sortByRating) {
        return jdbcMovieDaoImpl.getSortingByRating(sortByRating);
    }

    public List<Movie> getSortingByPrice(String sortByPrice) {
        return jdbcMovieDaoImpl.getSortingByPrice(sortByPrice);
    }


}
