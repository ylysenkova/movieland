package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import com.ylysenkova.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public List<Movie> getThreeMovies() {
        return movieDao.getThreeMovies(movieDao.getThreeMovieIds());
    }

    public List<Movie> getMovieByGenreId(int genreId) {
        return movieDao.getMovieByGenreId(genreId);
    }

    public List<Movie> getAllSorted(String field, Sorting direction) {
        return movieDao.getAllMoviesSorted(field, direction);
    }

    @Override
    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction) {
        return movieDao.getMoviesByGenreSorted(genreId, field, direction);
    }

}
