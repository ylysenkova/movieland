package com.ylysenkova.service;

import com.ylysenkova.dao.jdbc.impl.MovieDaoImpl;
import com.ylysenkova.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
@Service
public class MovieService {

    public List<Movie> getAllMovies () {
        MovieDaoImpl movieDao = new MovieDaoImpl();
        return movieDao.getAllMovies();
    }
}
