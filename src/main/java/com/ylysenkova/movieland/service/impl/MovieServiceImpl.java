package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.jdbc.impl.JdbcMovieDaoImpl;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.serviceInterface.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    JdbcMovieDaoImpl jdbcMovieDaoImpl;

    @Override
    public List<Movie> getAllMovies () {
      return jdbcMovieDaoImpl.getAllMovies();
    }

    @Override
    public List<Movie> getThreeMovies() {
        return jdbcMovieDaoImpl.getThreeMovies(jdbcMovieDaoImpl.getThreeMovieIds());
    }

    public List<Movie> getMovieByGenreId(int genreId) {
        return jdbcMovieDaoImpl.getMovieByGenreId(genreId);
    }


}
